package com.TaehyunDev.utils.managers;

import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

public class networkManager implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        try {
            URL url = new URL("https://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
