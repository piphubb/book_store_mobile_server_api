package com.piphub.springjwtauthentication.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AESCipherEncrypter {
    private static final String AES_KEY = "HG58YZ3CR9";

    private static final String ONE_SIGNAL_AES_KEY = "IK69ZA4DS0";

    public static String encrypt(String orignalText) throws Throwable {

        String encodedText = null;

        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            final byte[] ase = AES_KEY.getBytes("utf-8");
            final byte[] digestOfPassword = md.digest(AES_KEY.getBytes("utf-8"));
            final SecretKey key = new SecretKeySpec(digestOfPassword, "AES");

            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            final IvParameterSpec iv = new IvParameterSpec(new byte[16]);

            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            final byte[] plainTextBytes = orignalText.getBytes("utf-8");
            final byte[] encodeTextBytes = cipher.doFinal(plainTextBytes);
            final byte[] encodeTextBytes1 = cipher.doFinal(encodeTextBytes);

            encodedText = new Base64().encodeToString(encodeTextBytes);


        } catch (NoSuchAlgorithmException |
                 UnsupportedEncodingException |
                 IllegalBlockSizeException |
                 InvalidKeyException |
                 BadPaddingException |
                 NoSuchPaddingException | InvalidAlgorithmParameterException e) {

            throw new Exception(e);
        }

        return encodedText;
    }

    public static String encryptWithKey(String orignalText, String strKey) throws Throwable {

        String encodedText = null;

        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            final byte[] ase = strKey.getBytes("utf-8");
            final byte[] digestOfPassword = md.digest(strKey.getBytes("utf-8"));
            final SecretKey key = new SecretKeySpec(digestOfPassword, "AES");

            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            final IvParameterSpec iv = new IvParameterSpec(new byte[16]);

            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            final byte[] plainTextBytes = orignalText.getBytes("utf-8");
            final byte[] encodeTextBytes = cipher.doFinal(plainTextBytes);

            encodedText = new Base64().encodeToString(encodeTextBytes);


        } catch (NoSuchAlgorithmException |
                 UnsupportedEncodingException |
                 IllegalBlockSizeException |
                 InvalidKeyException |
                 BadPaddingException |
                 NoSuchPaddingException | InvalidAlgorithmParameterException e) {

            throw new Exception(e);
        }

        return encodedText;
    }

    public static String encryptContentSMS(String orignalText) throws Throwable {

        String encodedText = null;

        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            final byte[] ase = ONE_SIGNAL_AES_KEY.getBytes("utf-8");
            final byte[] digestOfPassword = md.digest(ONE_SIGNAL_AES_KEY.getBytes("utf-8"));
            final SecretKey key = new SecretKeySpec(digestOfPassword, "AES");

            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            final IvParameterSpec iv = new IvParameterSpec(new byte[16]);

            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            final byte[] plainTextBytes = orignalText.getBytes("utf-8");
            final byte[] encodeTextBytes = cipher.doFinal(plainTextBytes);

            encodedText = new Base64().encodeToString(encodeTextBytes);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | IllegalBlockSizeException
                 | InvalidKeyException | BadPaddingException | NoSuchPaddingException
                 | InvalidAlgorithmParameterException e) {
            throw new Exception(e);
        }

        return encodedText;
    }

    public static String decrypt(String orignalText) throws Throwable {

        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            final byte[] digestOfPassword = md.digest(AES_KEY.getBytes("utf-8"));
            final SecretKey key = new SecretKeySpec(digestOfPassword, "AES");

            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
            final byte[] plainTextBytes = Base64.decodeBase64(orignalText);
            final byte[] encodeTextBytes = cipher.doFinal(plainTextBytes);

            return new String(encodeTextBytes);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | IllegalBlockSizeException
                 | InvalidKeyException | BadPaddingException | NoSuchPaddingException
                 | InvalidAlgorithmParameterException e) {
            throw new Exception(e);
        }
    }

    public static String decryptWithKey(String orignalText, String strKey) throws Throwable {

        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            final byte[] digestOfPassword = md.digest(strKey.getBytes("utf-8"));
            final SecretKey key = new SecretKeySpec(digestOfPassword, "AES");

            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
            final byte[] plainTextBytes = Base64.decodeBase64(orignalText);
            final byte[] encodeTextBytes = cipher.doFinal(plainTextBytes);

            return new String(encodeTextBytes);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | IllegalBlockSizeException
                 | InvalidKeyException | BadPaddingException | NoSuchPaddingException
                 | InvalidAlgorithmParameterException e) {
            throw new Exception(e);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(AESCipherEncrypter.encrypt("2800"));
            System.out.println(AESCipherEncrypter.decrypt("13+4WLhpwSYuQr02xAkHOw=="));
        } catch (Throwable e) {
            // TODO Auto-generated catch block
        }

    }

}