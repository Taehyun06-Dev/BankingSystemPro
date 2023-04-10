package com.TaehyunDev.Ui.pre;

import com.TaehyunDev.utils.Updater;
import com.TaehyunDev.utils.managers.threadManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class controllerPre implements Initializable {

    @FXML
    private ImageView img_db;

    @FXML
    private ImageView img_network;

    @FXML
    private ImageView img_system;

    double x, y = 0;


    public void setNetworkStat(Boolean stat){
        if(stat){
            Platform.runLater(() -> {
                img_network.setImage(new Image(getClass().getResourceAsStream("/Images/icons8-ok-16.png")));
                img_network.setVisible(true);
            });
        }else{
            Platform.runLater(() -> {
                img_network.setImage(new Image(getClass().getResourceAsStream("/Images/icons8-close-16.png")));
                img_network.setVisible(true);
            });
        }
    }
    
    public void setSystemStat(Boolean stat){
        if(stat){
            Platform.runLater(() -> {
                img_system.setImage(new Image(getClass().getResourceAsStream("/Images/icons8-ok-16.png")));
                img_system.setVisible(true);
            });
        }else{
            Platform.runLater(() -> {
                img_system.setImage(new Image(getClass().getResourceAsStream("/Images/icons8-close-16.png")));
                img_system.setVisible(true);
            });
        }
    }

    public void setDBStat(Boolean stat){
        if(stat){
            Platform.runLater(() -> {
                img_db.setImage(new Image(getClass().getResourceAsStream("/Images/icons8-ok-16.png")));
                img_db.setVisible(true);
            });
        }else{
            Platform.runLater(() -> {
                img_db.setImage(new Image(getClass().getResourceAsStream("/Images/icons8-close-16.png")));
                img_db.setVisible(true);
            });
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        img_db.setVisible(false);
        img_network.setVisible(false);
        img_system.setVisible(false);
        threadManager manager = new threadManager();
        manager.init();
        new Updater(this);
        manager.runPre();

    }
}