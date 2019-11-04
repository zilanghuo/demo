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

    public static final String PUBLIC_ENCODE = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdVxJztryoRRZceYLjEhsMMxx0v2rg5+bTdWj+IrJOKgFk6N03rHQVhr39uHSBhcdf/NaFKHL3S+xJWe0bbqcyTV8P2BR5h5gZYdI8ypUEilSnRxHNlo18HrAxVuQAFi6vef73MJ/jiMpm+Br+I2yqoieS3QFYw/D0MhtO2yD3ywIDAQAB";

    public static final String PRIVATE_DECODE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ1XEnO2vKhFFlx5guMSGwwzHHS/auDn5tN1aP4isk4qAWTo3TesdBWGvf24dIGFx1/81oUocvdL7ElZ7RtupzJNXw/YFHmHmBlh0jzKlQSKVKdHEc2WjXwesDFW5AAWLq95/vcwn+OIymb4Gv4jbKqiJ5LdAVjD8PQyG07bIPfLAgMBAAECgYA+FFbhdR5mviuefQsZkNE/NQNrRQNkjdtTX6X3b7UuhgBa0k5GYzLCM5HPA/JkEGakBtyVWWpTDI/EOUkGY9hvryfiqK405PF6c4DLITH6H77/mDYoYeTuh66Q84urhh90bIjXpNzYYQOO7YuQTjLDn25Qcz3lMSlQIcwTBD2m0QJBAN/xERKTWPNVKATc7xjiyGHkxZ8035fbY6skgz2UgvDPBfeNSDsDEr++Xq99m94ZmrS1gTYlwVTNjbLxpyYi4ecCQQCz3TdV9bx/DTDLRiR4PoITeh/0q+DJmQEPmhap92fm8/ZLuQ/x57BGPvCcdv4GAwfTw9iSTS6y+Dj7+5JHoMZ9AkB+mrAXO1xvfTY5wZofDJ9S0N51CpRaKvsnF2ZnGx4H/FKYSLSMQEmKQ5sORcon/3u6LF8XeLLbObahcwMXiWMDAkAil1zZD1WdKbBEp8zDw2HI1SfoKlYk72Qg2AWb0pUM+cZYcdL7Nw/Tsq8bqFbaWSLcO0IfgPCGQu7Sp2NOm0ERAkAEdxTlHs8qT5KpCQx9bzgaYdNnDMJE1XqiqWG0T4dbpFQG8DOkR1+4ykVTjfHi0eWFUxP3n8yy+D+k5wGxmQPd";

    public static final String str = "hello world";

    public static void main(String[] args) throws Exception {
        PublicKey publicKey = generatePublicKey(PUBLIC_ENCODE);
        PrivateKey privateKey = generatePrivateKey(PRIVATE_DECODE);
        byte[] encode = rsaEncrypt(str.getBytes(), publicKey);
        System.out.println(encode);
        byte[] s = rsaDecrypt(encode, privateKey);
        System.out.println(new String(s));

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
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            //2、初始化，设置秘钥长度
            generator.initialize(keySize);
            //3、生成，并且返回
            ret = generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
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
                Cipher cipher = Cipher.getInstance("RSA");
                //设置Key
                cipher.init(Cipher.ENCRYPT_MODE, key);
                ret = cipher.doFinal(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
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
                Cipher cipher = Cipher.getInstance("RSA");
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
