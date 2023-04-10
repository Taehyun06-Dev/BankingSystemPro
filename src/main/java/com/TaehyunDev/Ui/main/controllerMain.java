package com.TaehyunDev.Ui.main;

import com.TaehyunDev.Ui.stageManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


public class controllerMain implements Initializable {

    @FXML
    private Pane pane_deposit;

    @FXML
    private Pane pane_search;

    @FXML
    private Pane pane_setting;

    @FXML
    private Pane pane_withdraw;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stageManager manager = new stageManager();
        pane_deposit.setOnMousePressed(e -> {
            manager.switchStage("ui_Main", "ui_Deposit");
        });
        pane_search.setOnMousePressed(e -> {
            manager.switchStage("ui_Main", "ui_Search");
        });
        pane_setting.setOnMousePressed(e -> {
            manager.switchStage("ui_Main", "ui_Setting");
        });
        pane_withdraw.setOnMousePressed(e -> {
            manager.switchStage("ui_Main", "ui_Withdraw");
        });
    }

}
