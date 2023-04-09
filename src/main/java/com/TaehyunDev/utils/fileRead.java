package com.TaehyunDev.utils;

import com.TaehyunDev.Data.fileData;
import com.TaehyunDev.Data.userAccount;
import com.TaehyunDev.Data.userData;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;

public class fileRead implements Callable<fileData> {

    private Boolean validateFile() throws Exception {

        Path path = Paths.get(System.getProperty("user.dir") + "BankingData/data.ser");
        long fileMilli = Files.getLastModifiedTime(path).toMillis();

        long decrpytMilli = Long.parseLong(new securityManager().decrypt
                (new userData().getAccount(-999).getUserName().replaceAll("THDBS", "")));

        if(Math.abs(fileMilli - decrpytMilli) < 2000){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public fileData call() throws Exception {

        File file = new File(System.getProperty("user.dir") + "BankingData/data.ser");
        userData data = new userData();

        if(!file.exists()){
            new File(System.getProperty("user.dir") + "BankingData").mkdir();
            file.createNewFile();
            new threadManager().saveData();
            return new fileData(true, true);
        }
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        data.setTotalMap((Map<Integer, userAccount>) ois.readObject());
        ois.close();
        fis.close();
        return new fileData(true, validateFile());
    }

}
