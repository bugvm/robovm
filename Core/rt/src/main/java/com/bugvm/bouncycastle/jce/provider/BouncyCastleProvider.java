package com.bugvm.bouncycastle.jce.provider;

import java.io.IOException;
import java.security.AccessController;
import java.security.PrivateKey;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import com.bugvm.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.bugvm.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import com.bugvm.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import com.bugvm.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import com.bugvm.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import com.bugvm.bouncycastle.jcajce.provider.util.AlgorithmProvider;
import com.bugvm.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;

/**
 * To add the provider at runtime use:
 * <pre>
 * import java.security.Security;
 * import com.bugvm.bouncycastle.jce.provider.BouncyCastleProvider;
 *
 * Security.addProvider(new BouncyCastleProvider());
 * </pre>
 * The provider can also be configured as part of your environment via
 * static registration by adding an entry to the java.security properties
 * file (found in $JAVA_HOME/jre/lib/security/java.security, where
 * $JAVA_HOME is the location of your JDK/JRE distribution). You'll find
 * detailed instructions in the file but basically it comes down to adding
 * a line:
 * <pre>
 * <code>
 *    security.provider.&lt;n&gt;=com.bugvm.bouncycastle.jce.provider.BouncyCastleProvider
 * </code>
 * </pre>
 * Where &lt;n&gt; is the preference you want the provider at (1 being the
 * most preferred).
 * <p>Note: JCE algorithm names should be upper-case only so the case insensitive
 * test for getInstance works.
 */
public final class BouncyCastleProvider extends Provider
    implements ConfigurableProvider
{
    private static String info = "BouncyCastle Security Provider v1.49";

    public static final String PROVIDER_NAME = "BC";

    public static final ProviderConfiguration CONFIGURATION = new BouncyCastleProviderConfiguration();

    private static final Map keyInfoConverters = new HashMap();

    /*
     * Configurable symmetric ciphers
     */
    private static final String SYMMETRIC_PACKAGE = "com.bugvm.bouncycastle.jcajce.provider.symmetric.";

    private static final String[] SYMMETRIC_GENERIC =
    {
        "PBEPBKDF2", "PBEPKCS12"
    };

    private static final String[] SYMMETRIC_MACS =
    {
        // BEGIN android-removed
        // "SipHash"
        // END android-removed
    };

    private static final String[] SYMMETRIC_CIPHERS =
    {
        // BEGIN android-removed
        // "AES", "ARC4", "Blowfish", "Camellia", "CAST5", "CAST6", "DES", "DESede", "GOST28147", "Grainv1", "Grain128", "HC128", "HC256", "IDEA",
        // "Noekeon", "RC2", "RC5", "RC6", "Rijndael", "Salsa20", "SEED", "Serpent", "Skipjack", "TEA", "Twofish", "VMPC", "VMPCKSA3", "XTEA"
        // END android-removed
        // BEGIN android-added
        "AES", "ARC4", "Blowfish", "DES", "DESede", "RC2", "Twofish"
        // END android-added
    };

     /*
     * Configurable asymmetric ciphers
     */
    private static final String ASYMMETRIC_PACKAGE = "com.bugvm.bouncycastle.jcajce.provider.asymmetric.";

    // this one is required for GNU class path - it needs to be loaded first as the
    // later ones configure it.
    private static final String[] ASYMMETRIC_GENERIC =
    {
        // BEGIN android-removed
        // "X509", "IES"
        // END android-removed
        // BEGIN android-added
        "X509"
        // END android-added
    };

    private static final String[] ASYMMETRIC_CIPHERS =
    {
        // BEGIN android-removed
        // "DSA", "DH", "EC", "RSA", "GOST", "ECGOST", "ElGamal", "DSTU4145"
        // END android-removed
        // BEGIN android-added
        "DSA", "DH", "EC", "RSA",
        // END android-added
    };

    /*
     * Configurable digests
     */
    private static final String DIGEST_PACKAGE = "com.bugvm.bouncycastle.jcajce.provider.digest.";
    private static final String[] DIGESTS =
    {
        // BEGIN android-removed
        // "GOST3411", "MD2", "MD4", "MD5", "SHA1", "RIPEMD128", "RIPEMD160", "RIPEMD256", "RIPEMD320", "SHA224", "SHA256", "SHA384", "SHA512", "SHA3", "Tiger", "Whirlpool"
        // END android-removed
        // BEGIN android-added
        "MD5", "SHA1", "SHA256", "SHA384", "SHA512",
        // END android-added
    };

    /*
     * Configurable digests
     */
    private static final String KEYSTORE_PACKAGE = "com.bugvm.bouncycastle.jcajce.provider.keystore.";
    private static final String[] KEYSTORES =
    {
        "BC", "PKCS12"
    };

    /**
     * Construct a new provider.  This should only be required when
     * using runtime registration of the provider using the
     * <code>Security.addProvider()</code> mechanism.
     */
    public BouncyCastleProvider()
    {
        super(PROVIDER_NAME, 1.49, info);

        AccessController.doPrivileged(new PrivilegedAction()
        {
            public Object run()
            {
                setup();
                return null;
            }
        });
    }

    private void setup()
    {
        loadAlgorithms(DIGEST_PACKAGE, DIGESTS);

        loadAlgorithms(SYMMETRIC_PACKAGE, SYMMETRIC_GENERIC);

        loadAlgorithms(SYMMETRIC_PACKAGE, SYMMETRIC_MACS);

        loadAlgorithms(SYMMETRIC_PACKAGE, SYMMETRIC_CIPHERS);

        loadAlgorithms(ASYMMETRIC_PACKAGE, ASYMMETRIC_GENERIC);

        loadAlgorithms(ASYMMETRIC_PACKAGE, ASYMMETRIC_CIPHERS);

        loadAlgorithms(KEYSTORE_PACKAGE, KEYSTORES);

        // BEGIN android-removed
        // //
        // // X509Store
        // //
        // put("X509Store.CERTIFICATE/COLLECTION", "com.bugvm.bouncycastle.jce.provider.X509StoreCertCollection");
        // put("X509Store.ATTRIBUTECERTIFICATE/COLLECTION", "com.bugvm.bouncycastle.jce.provider.X509StoreAttrCertCollection");
        // put("X509Store.CRL/COLLECTION", "com.bugvm.bouncycastle.jce.provider.X509StoreCRLCollection");
        // put("X509Store.CERTIFICATEPAIR/COLLECTION", "com.bugvm.bouncycastle.jce.provider.X509StoreCertPairCollection");
        //
        // put("X509Store.CERTIFICATE/LDAP", "com.bugvm.bouncycastle.jce.provider.X509StoreLDAPCerts");
        // put("X509Store.CRL/LDAP", "com.bugvm.bouncycastle.jce.provider.X509StoreLDAPCRLs");
        // put("X509Store.ATTRIBUTECERTIFICATE/LDAP", "com.bugvm.bouncycastle.jce.provider.X509StoreLDAPAttrCerts");
        // put("X509Store.CERTIFICATEPAIR/LDAP", "com.bugvm.bouncycastle.jce.provider.X509StoreLDAPCertPairs");
        //
        // //
        // // X509StreamParser
        // //
        // put("X509StreamParser.CERTIFICATE", "com.bugvm.bouncycastle.jce.provider.X509CertParser");
        // put("X509StreamParser.ATTRIBUTECERTIFICATE", "com.bugvm.bouncycastle.jce.provider.X509AttrCertParser");
        // put("X509StreamParser.CRL", "com.bugvm.bouncycastle.jce.provider.X509CRLParser");
        // put("X509StreamParser.CERTIFICATEPAIR", "com.bugvm.bouncycastle.jce.provider.X509CertPairParser");
        //
        // //
        // // cipher engines
        // //
        // put("Cipher.BROKENPBEWITHMD5ANDDES", "com.bugvm.bouncycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithMD5AndDES");
        //
        // put("Cipher.BROKENPBEWITHSHA1ANDDES", "com.bugvm.bouncycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithSHA1AndDES");
        //
        //
        // put("Cipher.OLDPBEWITHSHAANDTWOFISH-CBC", "com.bugvm.bouncycastle.jce.provider.BrokenJCEBlockCipher$OldPBEWithSHAAndTwofish");
        //
        // // Certification Path API
        // put("CertPathValidator.RFC3281", "com.bugvm.bouncycastle.jce.provider.PKIXAttrCertPathValidatorSpi");
        // put("CertPathBuilder.RFC3281", "com.bugvm.bouncycastle.jce.provider.PKIXAttrCertPathBuilderSpi");
        // put("CertPathValidator.RFC3280", "com.bugvm.bouncycastle.jce.provider.PKIXCertPathValidatorSpi");
        // put("CertPathBuilder.RFC3280", "com.bugvm.bouncycastle.jce.provider.PKIXCertPathBuilderSpi");
        // END android-removed
        put("CertPathValidator.PKIX", "com.bugvm.bouncycastle.jce.provider.PKIXCertPathValidatorSpi");
        put("CertPathBuilder.PKIX", "com.bugvm.bouncycastle.jce.provider.PKIXCertPathBuilderSpi");
        put("CertStore.Collection", "com.bugvm.bouncycastle.jce.provider.CertStoreCollectionSpi");
        // BEGIN android-removed
        // put("CertStore.LDAP", "com.bugvm.bouncycastle.jce.provider.X509LDAPCertStoreSpi");
        // put("CertStore.Multi", "com.bugvm.bouncycastle.jce.provider.MultiCertStoreSpi");
        // put("Alg.Alias.CertStore.X509LDAP", "LDAP");
        // END android-removed
    }

    private void loadAlgorithms(String packageName, String[] names)
    {
        for (int i = 0; i != names.length; i++)
        {
            Class clazz = null;
            try
            {
                ClassLoader loader = this.getClass().getClassLoader();

                if (loader != null)
                {
                    clazz = loader.loadClass(packageName + names[i] + "$Mappings");
                }
                else
                {
                    clazz = Class.forName(packageName + names[i] + "$Mappings");
                }
            }
            catch (ClassNotFoundException e)
            {
                // ignore
            }

            if (clazz != null)
            {
                try
                {
                    ((AlgorithmProvider)clazz.newInstance()).configure(this);
                }
                catch (Exception e)
                {   // this should never ever happen!!
                    throw new InternalError("cannot create instance of "
                        + packageName + names[i] + "$Mappings : " + e);
                }
            }
        }
    }

    public void setParameter(String parameterName, Object parameter)
    {
        synchronized (CONFIGURATION)
        {
            ((BouncyCastleProviderConfiguration)CONFIGURATION).setParameter(parameterName, parameter);
        }
    }

    public boolean hasAlgorithm(String type, String name)
    {
        return containsKey(type + "." + name) || containsKey("Alg.Alias." + type + "." + name);
    }

    public void addAlgorithm(String key, String value)
    {
        if (containsKey(key))
        {
            throw new IllegalStateException("duplicate provider key (" + key + ") found");
        }

        put(key, value);
    }

    public void addKeyInfoConverter(ASN1ObjectIdentifier oid, AsymmetricKeyInfoConverter keyInfoConverter)
    {
        keyInfoConverters.put(oid, keyInfoConverter);
    }

    public static PublicKey getPublicKey(SubjectPublicKeyInfo publicKeyInfo)
        throws IOException
    {
        AsymmetricKeyInfoConverter converter = (AsymmetricKeyInfoConverter)keyInfoConverters.get(publicKeyInfo.getAlgorithm().getAlgorithm());

        if (converter == null)
        {
            return null;
        }

        return converter.generatePublic(publicKeyInfo);
    }

    public static PrivateKey getPrivateKey(PrivateKeyInfo privateKeyInfo)
        throws IOException
    {
        AsymmetricKeyInfoConverter converter = (AsymmetricKeyInfoConverter)keyInfoConverters.get(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm());

        if (converter == null)
        {
            return null;
        }

        return converter.generatePrivate(privateKeyInfo);
    }
}
