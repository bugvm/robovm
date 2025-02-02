package com.bugvm.bouncycastle.operator.bc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// BEGIN android-removed
// import com.bugvm.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
// END android-removed
import com.bugvm.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import com.bugvm.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import com.bugvm.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import com.bugvm.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.bugvm.bouncycastle.crypto.ExtendedDigest;
// BEGIN android-removed
// import com.bugvm.bouncycastle.crypto.digests.GOST3411Digest;
// import com.bugvm.bouncycastle.crypto.digests.MD2Digest;
// import com.bugvm.bouncycastle.crypto.digests.MD4Digest;
// END android-removed
import com.bugvm.bouncycastle.crypto.digests.MD5Digest;
// BEGIN android-removed
// import com.bugvm.bouncycastle.crypto.digests.RIPEMD128Digest;
// import com.bugvm.bouncycastle.crypto.digests.RIPEMD160Digest;
// import com.bugvm.bouncycastle.crypto.digests.RIPEMD256Digest;
// END android-removed
import com.bugvm.bouncycastle.crypto.digests.SHA1Digest;
// BEGIN android-removed
// import com.bugvm.bouncycastle.crypto.digests.SHA224Digest;
// END android-removed
import com.bugvm.bouncycastle.crypto.digests.SHA256Digest;
import com.bugvm.bouncycastle.crypto.digests.SHA384Digest;
import com.bugvm.bouncycastle.crypto.digests.SHA512Digest;
import com.bugvm.bouncycastle.operator.OperatorCreationException;

public class BcDefaultDigestProvider
    implements BcDigestProvider
{
    private static final Map lookup = createTable();

    private static Map createTable()
    {
        Map table = new HashMap();

        table.put(OIWObjectIdentifiers.idSHA1, new BcDigestProvider()
        {
            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new SHA1Digest();
            }
        });
        // BEGIN android-removed
        // table.put(NISTObjectIdentifiers.id_sha224, new BcDigestProvider()
        // {
        //     public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
        //     {
        //         return new SHA224Digest();
        //     }
        // });
        // END android-removed
        table.put(NISTObjectIdentifiers.id_sha256, new BcDigestProvider()
        {
            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new SHA256Digest();
            }
        });
        table.put(NISTObjectIdentifiers.id_sha384, new BcDigestProvider()
        {
            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new SHA384Digest();
            }
        });
        table.put(NISTObjectIdentifiers.id_sha512, new BcDigestProvider()
        {
            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new SHA512Digest();
            }
        });
        table.put(PKCSObjectIdentifiers.md5, new BcDigestProvider()
        {
            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new MD5Digest();
            }
        });
        // BEGIN android-removed
        // table.put(PKCSObjectIdentifiers.md4, new BcDigestProvider()
        // {
        //     public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
        //     {
        //         return new MD4Digest();
        //     }
        // });
        // table.put(PKCSObjectIdentifiers.md2, new BcDigestProvider()
        // {
        //     public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
        //     {
        //         return new MD2Digest();
        //     }
        // });
        // table.put(CryptoProObjectIdentifiers.gostR3411, new BcDigestProvider()
        // {
        //     public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
        //     {
        //         return new GOST3411Digest();
        //     }
        // });
        // table.put(TeleTrusTObjectIdentifiers.ripemd128, new BcDigestProvider()
        // {
        //     public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
        //     {
        //         return new RIPEMD128Digest();
        //     }
        // });
        // table.put(TeleTrusTObjectIdentifiers.ripemd160, new BcDigestProvider()
        // {
        //     public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
        //     {
        //         return new RIPEMD160Digest();
        //     }
        // });
        // table.put(TeleTrusTObjectIdentifiers.ripemd256, new BcDigestProvider()
        // {
        //     public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
        //     {
        //         return new RIPEMD256Digest();
        //     }
        // });
        // END android-removed

        return Collections.unmodifiableMap(table);
    }

    public static final BcDigestProvider INSTANCE = new BcDefaultDigestProvider();

    private BcDefaultDigestProvider()
    {

    }

    public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
        throws OperatorCreationException
    {
        BcDigestProvider extProv = (BcDigestProvider)lookup.get(digestAlgorithmIdentifier.getAlgorithm());

        if (extProv == null)
        {
            throw new OperatorCreationException("cannot recognise digest");
        }

        return extProv.get(digestAlgorithmIdentifier);
    }
}
