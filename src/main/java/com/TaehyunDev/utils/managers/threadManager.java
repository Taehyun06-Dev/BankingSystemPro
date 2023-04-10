package com.TaehyunDev.utils.managers;


import com.TaehyunDev.utils.file.fileWrite;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadManager {
    private static ExecutorService executorService;

    public void init(){
        executorService = Executors.newFixedThreadPool(4);
    }

    public void runPre(){executorService.submit(new processManager(executorService));}

    public void saveData(){
        executorService.submit(new fileWrite());
    }

}
