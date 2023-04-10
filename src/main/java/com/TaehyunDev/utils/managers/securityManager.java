package com.TaehyunDev.utils.managers;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;


public class securityManager {

    private final String secretKeyTH = "THBANKDBSECURITY";

    public SecretKeySpec getKey(final String myKey) throws Exception{
        byte[] key;
        MessageDigest sha = null;
        key = myKey.getBytes("UTF-8");
        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        return new SecretKeySpec(key, "AES");
    }

    public String encrypt(final String strToEncrypt) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getKey(secretKeyTH));
        return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    }

    public String decrypt(final String strToDecrypt) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, getKey(secretKeyTH));
        return new String(cipher.doFinal(Base64.getDecoder()
                    .decode(strToDecrypt)));
    }
}
