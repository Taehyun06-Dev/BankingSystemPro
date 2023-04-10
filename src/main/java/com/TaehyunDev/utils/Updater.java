package com.TaehyunDev.utils;

import com.TaehyunDev.Ui.pre.controllerPre;
import javafx.application.Platform;

public class Updater {

    private static controllerPre controller;

    public Updater(){}

    public Updater(controllerPre controller){Updater.controller = controller;}

    public void setNetwork(Boolean stat){controller.setNetworkStat(stat);}

    public void setSystem(Boolean stat){controller.setSystemStat(stat);}

    public void setDBStat(Boolean stat){controller.setDBStat(stat);}


}
