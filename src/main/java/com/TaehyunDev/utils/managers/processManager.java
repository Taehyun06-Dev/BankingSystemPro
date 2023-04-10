package com.TaehyunDev.utils.managers;

import com.TaehyunDev.Data.fileData;
import com.TaehyunDev.Ui.stageManager;
import com.TaehyunDev.utils.Updater;
import com.TaehyunDev.utils.file.fileRead;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class processManager implements Runnable{

    private ExecutorService executorService;

    public processManager(ExecutorService executorService){
        this.executorService = executorService;
    }
    @Override
    public void run() {
        Future<fileData> dataStat = executorService.submit(new fileRead());
        Future<Boolean> networkStat = executorService.submit(new networkManager());
        Updater updater = new Updater();
        try{
            Thread.sleep(1500);
            updater.setNetwork(networkStat.get());
            Thread.sleep(2500);
            fileData data = dataStat.get();
            Boolean isValid = data.getIsValid();
            updater.setSystem(data.getStat());
            updater.setDBStat(isValid);
            Thread.sleep(4000);
            if(isValid){
                new stageManager().switchStage("ui_Pre", "ui_Main");
            }else{
                new stageManager().switchStage("ui_Pre", "ui_Pre_Fail");
            }
        }catch (Exception e){
            new errorManager("init 단계 오류", e.getMessage());
            e.printStackTrace();
        }
    }
}
