package com.TaehyunDev.Ui;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

public class bankingUI extends Application {

    double x, y = 0;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Pre/BankingPre.fxml"));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/icons8-security-lock-50.png")));
        stage.setTitle("TH Security");
        stage.initStyle(StageStyle.UNDECORATED);

        new stageManager().registerStage("ui_pre", stage);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
           });
        stage.setScene(new Scene(root));
        stage.show();
        //stage.showAndwait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

