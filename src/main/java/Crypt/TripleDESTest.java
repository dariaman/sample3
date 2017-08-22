/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crypt;

/**
 *
 * @author dariaman.siagian
 */
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class TripleDESTest {

    private String Kunci = "D@riamanSiagian";
    private String Kunci2 = "D@riamanSiagian";
    private String Kunci3 = "D@riamanSiagian";

    public byte[] encrypt(String message) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest(Kunci
                .getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        final byte[] plainTextBytes = message.getBytes("utf-8");
        final byte[] cipherText = cipher.doFinal(plainTextBytes);
        // final String encodedCipherText = new sun.misc.BASE64Encoder()
        // .encode(cipherText);

        return cipherText;
    }

    public String decrypt(byte[] message) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest(Kunci
                .getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        decipher.init(Cipher.DECRYPT_MODE, key, iv);

        // final byte[] encData = new
        // sun.misc.BASE64Decoder().decodeBuffer(message);
        final byte[] plainText = decipher.doFinal(message);

        return new String(plainText, "UTF-8");
    }

    /** Read a TripleDES secret key from the specified file */
//private static SecretKey readKey(String keyStr) throws IOException, NoSuchAlgorithmException, InvalidKeyException,
//            InvalidKeySpecException {
//        // Convert Key Str to bytes
//        byte[] rawkey = new byte[1024];
//        rawkey = keyStr.getBytes("UTF-8");
//
//        // Convert the raw bytes to a secret key like this
//        DESedeKeySpec keyspec = new DESedeKeySpec(rawkey);
//        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
//        SecretKey key = keyfactory.generateSecret(keyspec);
//        return key;
//    }
//    private static byte[] encrypt1(byte[] inpBytes, SecretKey key) throws Exception {
//        Cipher cipher = Cipher.getInstance("DESede");
//        cipher.init(Cipher.ENCRYPT_MODE, key);
//        return cipher.doFinal(inpBytes);
//    }
//
//    private static byte[] decrypt1(byte[] inpBytes, SecretKey key) throws Exception {
//        Cipher cipher = Cipher.getInstance("DESede");
//        cipher.init(Cipher.DECRYPT_MODE, key);
//        return cipher.doFinal(inpBytes);
//    }
}
