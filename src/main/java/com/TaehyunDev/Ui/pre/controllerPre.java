package com.TaehyunDev.Ui.pre;

import com.TaehyunDev.Ui.stageManager;
import com.TaehyunDev.utils.Updater;
import com.TaehyunDev.utils.threadManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
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

    public void showError() {
        Platform.runLater(() -> {
            stageManager manager = new stageManager();
            manager.closeStage("ui_pre");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Fxml/BankingError.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/icons8-security-lock-50.png")));
                stage.setTitle("TH Security");
                stage.initStyle(StageStyle.UNDECORATED);

                root.setOnMousePressed(event -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });
                root.setOnMouseDragged(event -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });
                stage.show();
                manager.registerStage("ui_error", stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void openMain() {
        Platform.runLater(() -> {
            stageManager manager = new stageManager();
            manager.closeStage("ui_pre");
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/Fxml/BankingMain.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/icons8-card-exchange-50.png")));
            stage.setTitle("TH Banking");
            stage.initStyle(StageStyle.DECORATED);

            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });
            stage.show();
            manager.registerStage("ui_main", stage);
        });
    }

    public void showErrorDB() {
        Platform.runLater(() -> {
            stageManager manager = new stageManager();
            manager.closeStage("ui_pre");
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/Fxml/Pre/BankingPre_Fail.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/icons8-security-lock-50.png")));
            stage.setTitle("TH Security");
            stage.initStyle(StageStyle.UNDECORATED);

            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });
            stage.show();
            manager.registerStage("ui_pre_fail", stage);
        });
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