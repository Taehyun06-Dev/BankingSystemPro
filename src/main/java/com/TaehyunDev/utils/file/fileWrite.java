package com.TaehyunDev.utils.file;

import com.TaehyunDev.Data.userAccount;
import com.TaehyunDev.Data.userData;
import com.TaehyunDev.utils.managers.errorManager;
import com.TaehyunDev.utils.managers.securityManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class fileWrite implements Runnable{

    @Override
    public void run() {
        userData data = new userData();
        Map<Integer, userAccount> userDataMap = data.getDataMap();
        try{
            data.registerData(-999, 0000, 0,"THDBS"+new securityManager().encrypt(String.valueOf(System.currentTimeMillis())));
            FileOutputStream fos = new FileOutputStream(new File(System.getProperty("user.dir") + "BankingData/data.ser"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userDataMap);
            oos.close();
            fos.close();
        }catch (Exception e){
            new errorManager("파일 작성중 에러", e.getMessage());
            e.printStackTrace();
        }
    }
}
