package com.TaehyunDev.utils;

public class switchStage implements Runnable{

    private Boolean isValid;

    public switchStage(Boolean isValid){
        this.isValid = isValid;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(6000);
        } catch (Exception e) {
            errorManager.errorMsg = "switch wait중 오류";
            errorManager.errorDetail = e.getMessage();
            new Updater().showError();
        }
        if(isValid){
            new Updater().openMain();
        }else{
            new Updater().showErrorDB();
        }

    }
}
