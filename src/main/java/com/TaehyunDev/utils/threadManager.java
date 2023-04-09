package com.TaehyunDev.utils;


import com.TaehyunDev.Data.fileData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class threadManager {
    private static ExecutorService executorService;

    public void init(){
        executorService = Executors.newFixedThreadPool(4);
    }

    public void runPre(){
        executorService.submit(new processManager(executorService));
    }

    public void saveData(){
        executorService.submit(new fileWrite());
    }

}
