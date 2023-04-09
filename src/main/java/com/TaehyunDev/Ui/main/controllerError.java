package com.TaehyunDev.Ui.main;

import com.TaehyunDev.utils.errorManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class controllerError implements Initializable {

    @FXML
    private Button btt_exit;

    @FXML
    private Label lbl_errMsg;

    @FXML
    private TextArea txt_errDetail;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbl_errMsg.setText(errorManager.errorMsg);
        txt_errDetail.setEditable(false);
        if(errorManager.errorDetail != null){
            txt_errDetail.setText(errorManager.errorDetail);
        }
        btt_exit.setOnMouseClicked(e -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
