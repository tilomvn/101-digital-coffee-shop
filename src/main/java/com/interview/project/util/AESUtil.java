package com.interview.project.util;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESUtil {

    private final String algorithm = "AES/CBC/PKCS5Padding";
    private SecretKey key;
    private IvParameterSpec ivParameterSpec;

    public static AESUtil newInstance() { return new AESUtil(); }
    private AESUtil() {}

    public AESUtil setSecretKey(String secretKey) {
        key = generateKey(Base64.getEncoder().encodeToString(secretKey.getBytes()));
        ivParameterSpec = generateIv(secretKey);
        return this;
    }

    public String encrypt(String input) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        var cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
        var cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public String decrypt(String cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        var cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        var plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }

    private IvParameterSpec generateIv(String key) {
        var iv = key.getBytes();
        return new IvParameterSpec(iv);
    }

    private SecretKey generateKey(String encodedKey) {
        var decodedKey = Base64.getDecoder().decode(encodedKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }
}
