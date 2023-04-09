package com.TaehyunDev.Ui.pre;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class controllerFail implements Initializable {
    @FXML
    private Button btt_exit;

    @FXML
    private Button btt_reset;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btt_exit.setOnMouseClicked(e -> {
            Platform.exit();
            System.exit(0);
        });
        btt_reset.setOnMouseClicked(e ->{
            File file = new File(System.getProperty("user.dir") + "BankingData/data.ser");
            if(file.exists()){
                file.delete();
            }
            Platform.exit();
            System.exit(0);
        });
    }

}
