package com.TaehyunDev.utils;

import com.TaehyunDev.Data.fileData;

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
            Thread.sleep(2000);
            updater.setNetwork(networkStat.get());
            Thread.sleep(2300);
            final fileData data = dataStat.get();
            final Boolean isValid = data.getIsValid();
            updater.setSystem(data.getStat());
            updater.setDBStat(isValid);
            executorService.submit(new switchStage(isValid));
        }catch (Exception e){
            errorManager.errorMsg = "init 단계 오류";
            errorManager.errorDetail = e.getMessage();
            updater.showError();
        }

    }
}
