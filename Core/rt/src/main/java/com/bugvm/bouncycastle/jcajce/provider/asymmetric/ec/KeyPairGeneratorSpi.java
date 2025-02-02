package com.bugvm.bouncycastle.jcajce.provider.asymmetric.ec;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.util.Hashtable;

import com.bugvm.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.bugvm.bouncycastle.asn1.nist.NISTNamedCurves;
import com.bugvm.bouncycastle.asn1.sec.SECNamedCurves;
// BEGIN android-removed
// import com.bugvm.bouncycastle.asn1.teletrust.TeleTrusTNamedCurves;
// END android-removed
import com.bugvm.bouncycastle.asn1.x9.X962NamedCurves;
import com.bugvm.bouncycastle.asn1.x9.X9ECParameters;
import com.bugvm.bouncycastle.crypto.AsymmetricCipherKeyPair;
import com.bugvm.bouncycastle.crypto.generators.ECKeyPairGenerator;
import com.bugvm.bouncycastle.crypto.params.ECDomainParameters;
import com.bugvm.bouncycastle.crypto.params.ECKeyGenerationParameters;
import com.bugvm.bouncycastle.crypto.params.ECPrivateKeyParameters;
import com.bugvm.bouncycastle.crypto.params.ECPublicKeyParameters;
import com.bugvm.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import com.bugvm.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import com.bugvm.bouncycastle.jce.provider.BouncyCastleProvider;
import com.bugvm.bouncycastle.jce.spec.ECNamedCurveGenParameterSpec;
import com.bugvm.bouncycastle.jce.spec.ECNamedCurveSpec;
import com.bugvm.bouncycastle.jce.spec.ECParameterSpec;
import com.bugvm.bouncycastle.math.ec.ECCurve;
import com.bugvm.bouncycastle.math.ec.ECPoint;
import com.bugvm.bouncycastle.util.Integers;

public abstract class KeyPairGeneratorSpi
    extends java.security.KeyPairGenerator
{
    public KeyPairGeneratorSpi(String algorithmName)
    {
        super(algorithmName);
    }

    public static class EC
        extends KeyPairGeneratorSpi
    {
        ECKeyGenerationParameters   param;
        ECKeyPairGenerator          engine = new ECKeyPairGenerator();
        Object                      ecParams = null;
        int                         strength = 239;
        int                         certainty = 50;
        SecureRandom                random = new SecureRandom();
        boolean                     initialised = false;
        String                      algorithm;
        ProviderConfiguration       configuration;

        static private Hashtable    ecParameters;

        static {
            ecParameters = new Hashtable();

            ecParameters.put(Integers.valueOf(192), new ECGenParameterSpec("prime192v1")); // a.k.a P-192
            ecParameters.put(Integers.valueOf(239), new ECGenParameterSpec("prime239v1"));
            ecParameters.put(Integers.valueOf(256), new ECGenParameterSpec("prime256v1")); // a.k.a P-256

            ecParameters.put(Integers.valueOf(224), new ECGenParameterSpec("P-224"));
            ecParameters.put(Integers.valueOf(384), new ECGenParameterSpec("P-384"));
            ecParameters.put(Integers.valueOf(521), new ECGenParameterSpec("P-521"));
        }

        public EC()
        {
            super("EC");
            this.algorithm = "EC";
            this.configuration = BouncyCastleProvider.CONFIGURATION;
        }

        public EC(
            String  algorithm,
            ProviderConfiguration configuration)
        {
            super(algorithm);
            this.algorithm = algorithm;
            this.configuration = configuration;
        }

        public void initialize(
            int             strength,
            SecureRandom    random)
        {
            this.strength = strength;
            // BEGIN android-added
            if (random != null) {
            // END android-added
            this.random = random;
            // BEGIN android-added
            }
            // END android-added
            ECGenParameterSpec ecParams = (ECGenParameterSpec)ecParameters.get(Integers.valueOf(strength));

            if (ecParams != null)
            {
                try
                {
                    initialize(ecParams, random);
                }
                catch (InvalidAlgorithmParameterException e)
                {
                    throw new InvalidParameterException("key size not configurable.");
                }
            }
            else
            {
                throw new InvalidParameterException("unknown key size.");
            }
        }

        public void initialize(
            AlgorithmParameterSpec  params,
            SecureRandom            random)
            throws InvalidAlgorithmParameterException
        {
            // BEGIN android-added
            if (random == null) {
                random = this.random;
            }
            // END android-added
            if (params instanceof ECParameterSpec)
            {
                ECParameterSpec p = (ECParameterSpec)params;
                this.ecParams = params;

                param = new ECKeyGenerationParameters(new ECDomainParameters(p.getCurve(), p.getG(), p.getN()), random);

                engine.init(param);
                initialised = true;
            }
            else if (params instanceof java.security.spec.ECParameterSpec)
            {
                java.security.spec.ECParameterSpec p = (java.security.spec.ECParameterSpec)params;
                this.ecParams = params;

                ECCurve curve = EC5Util.convertCurve(p.getCurve());
                ECPoint g = EC5Util.convertPoint(curve, p.getGenerator(), false);

                param = new ECKeyGenerationParameters(new ECDomainParameters(curve, g, p.getOrder(), BigInteger.valueOf(p.getCofactor())), random);

                engine.init(param);
                initialised = true;
            }
            else if (params instanceof ECGenParameterSpec || params instanceof ECNamedCurveGenParameterSpec)
            {
                String curveName;

                if (params instanceof ECGenParameterSpec)
                {
                    curveName = ((ECGenParameterSpec)params).getName();
                }
                else
                {
                    curveName = ((ECNamedCurveGenParameterSpec)params).getName();
                }

                X9ECParameters  ecP = X962NamedCurves.getByName(curveName);
                if (ecP == null)
                {
                    ecP = SECNamedCurves.getByName(curveName);
                    if (ecP == null)
                    {
                        ecP = NISTNamedCurves.getByName(curveName);
                    }
                    // BEGIN android-removed
                    // if (ecP == null)
                    // {
                    //     ecP = TeleTrusTNamedCurves.getByName(curveName);
                    // }
                    // END android-removed
                    if (ecP == null)
                    {
                        // See if it's actually an OID string (SunJSSE ServerHandshaker setupEphemeralECDHKeys bug)
                        try
                        {
                            ASN1ObjectIdentifier oid = new ASN1ObjectIdentifier(curveName);
                            ecP = X962NamedCurves.getByOID(oid);
                            if (ecP == null)
                            {
                                ecP = SECNamedCurves.getByOID(oid);
                            }
                            if (ecP == null)
                            {
                                ecP = NISTNamedCurves.getByOID(oid);
                            }
                            // BEGIN android-removed
                            // if (ecP == null)
                            // {
                            //     ecP = TeleTrusTNamedCurves.getByOID(oid);
                            // }
                            // END android-removed
                            if (ecP == null)
                            {
                                throw new InvalidAlgorithmParameterException("unknown curve OID: " + curveName);
                            }
                        }
                        catch (IllegalArgumentException ex)
                        {
                            throw new InvalidAlgorithmParameterException("unknown curve name: " + curveName);
                        }
                    }
                }

                this.ecParams = new ECNamedCurveSpec(
                            curveName,
                            ecP.getCurve(),
                            ecP.getG(),
                            ecP.getN(),
                            ecP.getH(),
                            null); // ecP.getSeed());   Work-around JDK bug -- it won't look up named curves properly if seed is present

                java.security.spec.ECParameterSpec p = (java.security.spec.ECParameterSpec)ecParams;

                ECCurve curve = EC5Util.convertCurve(p.getCurve());
                ECPoint g = EC5Util.convertPoint(curve, p.getGenerator(), false);

                param = new ECKeyGenerationParameters(new ECDomainParameters(curve, g, p.getOrder(), BigInteger.valueOf(p.getCofactor())), random);

                engine.init(param);
                initialised = true;
            }
            else if (params == null && configuration.getEcImplicitlyCa() != null)
            {
                ECParameterSpec p = configuration.getEcImplicitlyCa();
                this.ecParams = params;

                param = new ECKeyGenerationParameters(new ECDomainParameters(p.getCurve(), p.getG(), p.getN()), random);

                engine.init(param);
                initialised = true;
            }
            else if (params == null && configuration.getEcImplicitlyCa() == null)
            {
                throw new InvalidAlgorithmParameterException("null parameter passed but no implicitCA set");
            }
            else
            {
                throw new InvalidAlgorithmParameterException("parameter object not a ECParameterSpec");
            }
        }

        public KeyPair generateKeyPair()
        {
            if (!initialised)
            {
                initialize(strength, new SecureRandom());
            }

            AsymmetricCipherKeyPair     pair = engine.generateKeyPair();
            ECPublicKeyParameters       pub = (ECPublicKeyParameters)pair.getPublic();
            ECPrivateKeyParameters      priv = (ECPrivateKeyParameters)pair.getPrivate();

            if (ecParams instanceof ECParameterSpec)
            {
                ECParameterSpec p = (ECParameterSpec)ecParams;

                BCECPublicKey pubKey = new BCECPublicKey(algorithm, pub, p, configuration);
                return new KeyPair(pubKey,
                                   new BCECPrivateKey(algorithm, priv, pubKey, p, configuration));
            }
            else if (ecParams == null)
            {
               return new KeyPair(new BCECPublicKey(algorithm, pub, configuration),
                                   new BCECPrivateKey(algorithm, priv, configuration));
            }
            else
            {
                java.security.spec.ECParameterSpec p = (java.security.spec.ECParameterSpec)ecParams;

                BCECPublicKey pubKey = new BCECPublicKey(algorithm, pub, p, configuration);
                
                return new KeyPair(pubKey, new BCECPrivateKey(algorithm, priv, pubKey, p, configuration));
            }
        }
    }

    public static class ECDSA
        extends EC
    {
        public ECDSA()
        {
            super("ECDSA", BouncyCastleProvider.CONFIGURATION);
        }
    }

    public static class ECDH
        extends EC
    {
        public ECDH()
        {
            super("ECDH", BouncyCastleProvider.CONFIGURATION);
        }
    }

    public static class ECDHC
        extends EC
    {
        public ECDHC()
        {
            super("ECDHC", BouncyCastleProvider.CONFIGURATION);
        }
    }

    public static class ECMQV
        extends EC
    {
        public ECMQV()
        {
            super("ECMQV", BouncyCastleProvider.CONFIGURATION);
        }
    }
}