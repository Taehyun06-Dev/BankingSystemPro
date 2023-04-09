package com.TaehyunDev;

import com.TaehyunDev.utils.securityManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class main {

    public static void main(String[] args) throws Exception {
        securityManager manager = new securityManager();
        String originalString = String.valueOf(System.currentTimeMillis());
        String encryptedString = manager.encrypt(originalString) ;
        String decryptedString = manager.decrypt(encryptedString) ;

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
    }
}
