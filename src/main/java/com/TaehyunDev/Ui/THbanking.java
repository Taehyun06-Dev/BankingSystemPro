package com.TaehyunDev.Ui;

public class THbanking {
    public static void main(String[] args){
        stageManager manager = new stageManager();
        manager.initStages();
        manager.switchStage(null, "ui_Pre");
    }
}
