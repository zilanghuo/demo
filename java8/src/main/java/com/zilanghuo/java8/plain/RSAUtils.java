package com.zilanghuo.java8.plain;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by laiwufa on 2019-11-01
 */
public class RSAUtils {

    public static final String PUBLIC_ENCODE = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDm1FeGNr7B7ukUwdY/q7Z2GlQA58RBtamysrRLH0lR5/1nTpyZf5zQGZw6whqeOeWq2MyCWM08XoXKIMYWme83UhxHnGb5NhtXOl4JM2el1DSEKM36r1AE7adzCvLy0a6QX7byl03luWk7jUX6gx6plyFyVlitVHOI/SWayVWd7wIDAQAB";

    public static final String PRIVATE_DECODE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAObUV4Y2vsHu6RTB1j+rtnYaVADnxEG1qbKytEsfSVHn/WdOnJl/nNAZnDrCGp455arYzIJYzTxehcogxhaZ7zdSHEecZvk2G1c6XgkzZ6XUNIQozfqvUATtp3MK8vLRrpBftvKXTeW5aTuNRfqDHqmXIXJWWK1Uc4j9JZrJVZ3vAgMBAAECgYEAt7Ff/aTvZXH09tnS4FSMHaySES5f1lL1OwpPxECfnpjZ5i5/HQCbEFhxJal2eVhl1vI0vb5hhZtLKTGvsijKXl8BAEU1EB39I1xHY3QF5vc8UxIM3g015wHJ0leSE8wMczS1xYHH8x4OA+n3hXYBpwWJZtU3O4eYGhJ0a4HVMaECQQD2DXliR/z/fgdJMHCZXnT9TWLT3sZjOl9ldE1UNxdlG3yeqfM5s5346OHY0das6tdMec/PHeKH6ZpJcdO1KHfZAkEA8ClQtG9wgtkIY2Vq1l1yKB9/lW7TaXyn+l5NdNHjxyBFI10xMwdVdOJ451afciauX/m2swX4/n1q045nepWvBwJAZsApuIkndepDUKUR4Ho5kK4IX0Hgxv9RZySyRkDbXDWmTPvbvnTru05FQi4e4AcR8bXMuklYmDlNXO5T4osLwQJBAOkU3/vOcB697pMxGahk1ucz8sof3lTxo8GtBnWLQwq+OfuhdZ91za18IyU6f9L00aXaIEPNVyP+qtAZ1Do3zb8CQQDRB8pr18nlCkDmjreU0I5YFgJPdsh+dB92PueO6zFVIBjsVdQBnF7Vts3PMzJ+3IPVnJgLOzmd+MhEpX8ytBqT";

    public static final String str = "222222";

    /**
     * String to hold name of the encryption padding.
     */
    public static final String PADDING = "RSA/NONE/NoPadding";

    /**
     * String to hold name of the security provider.
     */
    public static final String PROVIDER = "BC";

    public static final String ALGORITHM = "RSA";


    public static void main(String[] args) throws Exception {
        PublicKey publicKey = generatePublicKey(PUBLIC_ENCODE);
        PrivateKey privateKey = generatePrivateKey(PRIVATE_DECODE);
      /*  KeyPair keyPair = generateRSAKeyPair(1024);
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println(baseRsaPublicKey(publicKey));
        System.out.println(baseRsaPrivateKey(privateKey));*/

        byte[] encode = rsaEncrypt(str.getBytes(), publicKey);
        System.out.println(encode);
        String x = Base64.encodeBase64String(encode);
        System.out.println("base64:"+x);
        byte[] encodeStr = Base64.decodeBase64(x);
        byte[] s = rsaDecrypt(encodeStr, privateKey);
        System.out.println("明文："+new String(s));
        System.out.println("-------------------------");
        String x1 = baseRsaEncrypt(str, publicKey);
        System.out.println(x1);
        System.out.println(baseRsaDecrypt(x1,privateKey));
    }

    /**
     * 通过指定的密钥长度生成非对称的密钥对
     *
     * @param keySize 推荐使用1024,2048 ，不允许低于1024
     * @return
     */
    public static KeyPair generateRSAKeyPair(int keySize) {
        KeyPair ret = null;
        try {
            //1、准备生成
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM,PROVIDER);
            //2、初始化，设置秘钥长度
            generator.initialize(keySize);
            //3、生成，并且返回
            ret = generator.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }


    /**
     * RSA 加密
     *
     * @param data 需要加密的数据
     * @param key  可以是 PublicKey,也可以是PrivateKey
     * @return
     */
    public static byte[] rsaEncrypt(byte[] data, Key key) {
        byte[] ret = null;
        if (data != null
                && data.length > 0
                && key != null) {
            // 1、创建Cipher 使用RSA
            try {
                Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
                Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);
                //设置Key
                cipher.init(Cipher.ENCRYPT_MODE, key);
                ret = cipher.doFinal(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 转化为String
        return  ret;
    }

    /**
     * base64 加密
     * @param plaintext
     * @param publicKey
     * @return
     */
    public static String baseRsaEncrypt(String plaintext,Key publicKey){
        byte[] bytes = rsaEncrypt(plaintext.getBytes(), publicKey);
        return Base64.encodeBase64String(bytes);
    }

    /**
     * base64 解密
     * @param ciphertext
     * @param privatekey
     * @return
     */
    public static String baseRsaDecrypt(String ciphertext,Key privatekey){
        //base 解密
        byte[] bytes = Base64.decodeBase64(ciphertext);
        return new String(rsaDecrypt(bytes,privatekey));

    }

    /**
     * RSA 解密
     *
     * @param data 要解密的数据
     * @param key  可以是 PublicKey,也可以是PrivateKey
     * @return
     */
    public static byte[] rsaDecrypt(byte[] data, Key key) {
        byte[] ret = null;
        if (data != null
                && data.length > 0
                && key != null) {
            // 1、创建Cipher 使用RSA
            try {
                Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
                Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);
                //设置Key
                cipher.init(Cipher.DECRYPT_MODE, key);
                ret = cipher.doFinal(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static String baseRsaPrivateKey(PrivateKey privateKey) {
        byte[] privateKeyEncoded = privateKey.getEncoded();
        String privatekey = Base64.encodeBase64String(privateKeyEncoded);
        return privatekey;
    }

    /**
     * publicKey baseEncode
     *
     * @param publicKey
     * @return
     */
    public static String baseRsaPublicKey(PublicKey publicKey) {
        byte[] publicKeyEncoded = publicKey.getEncoded();
        String publickey = Base64.encodeBase64String(publicKeyEncoded);
        return publickey;
    }

    /**
     * 通过字符串生成publicKey
     *
     * @param publicKeyEncode
     * @return
     */
    public static PublicKey generatePublicKey(String publicKeyEncode) {
        //获取公钥
        byte[] publicdecode = Base64.decodeBase64(publicKeyEncode);
        //构造X509EncodedKeySpec对象
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicdecode);
        //指定加密算法
        KeyFactory keyFactory = null;
        PublicKey publicKey = null;
        try {
            //取得公钥
            keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(publicKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    /**
     * 获取到privateKey
     *
     * @param privateKeyEncode
     * @return
     */
    public static PrivateKey generatePrivateKey(String privateKeyEncode) {
        byte[] privatedecode = Base64.decodeBase64(privateKeyEncode);
        //构造X509EncodedKeySpec对象
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privatedecode);
        //指定加密算法
        KeyFactory keyFactory = null;
        PrivateKey privateKey = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(privateKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //取得私钥
        return privateKey;
    }

}
