package com.TaehyunDev.Ui;

import com.TaehyunDev.utils.managers.errorManager;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class stageManager {

    private static Map<String, Stage> stageMap = new HashMap<>();
    private static double x, y;

    public void registerStage(String stageName, Stage stage){
        if(!stageMap.containsKey(stageName)) {
            stageMap.put(stageName, stage);
        }
    }

    public void closeStage(String stageName) {
        Platform.runLater(() -> {
            if (stageMap.containsKey(stageName)) {
                stageMap.get(stageName).close();
                stageMap.remove(stageName);
            }
        });
    }

    public void switchStage(String stageFrom, String stageTo){
        if(stageFrom == null){
            Platform.runLater(() -> {
                if (stageMap.containsKey(stageTo)) {
                    stageMap.get(stageTo).show();
                }
            });
            return;
        }
        Platform.runLater(() -> {
            if (stageMap.containsKey(stageFrom) && stageMap.containsKey(stageTo)) {
                stageMap.get(stageFrom).hide();
                stageMap.get(stageTo).show();
            }
        });
    }

    private void closeAllStage(){
        for (String key : stageMap.keySet()) {
            Stage tempStage = stageMap.get(key);
            if(tempStage.isShowing()){
                tempStage.close();
            };
        }
    }

    private void initMenu() throws Exception {
        String[] tempList = {"Main", "Deposit", "Search", "Setting", "Withdraw"};
        Platform.runLater(() -> {
            for (String it : tempList) {
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Menu/Banking" + it + ".fxml"));
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
                    stage.setOnCloseRequest(t -> {
                        Platform.exit();
                        System.exit(0);
                    });
                    stage.setScene(new Scene(root));
                    registerStage("ui_" + it, stage);
                }catch (Exception e){
                    new errorManager("Menu UI 생성중 에러", e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    private void initPre() throws Exception {
        String[] tempList = {"Pre", "Pre_Fail", "Error"};
        Platform.runLater(() -> {
            for (String it : tempList) {
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Pre/Banking" + it + ".fxml"));
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
                    stage.setOnCloseRequest(t -> {
                        Platform.exit();
                        System.exit(0);
                    });
                    stage.setScene(new Scene(root));
                    registerStage("ui_" + it, stage);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void showError(){
        closeAllStage();
        Platform.runLater(() -> {
            stageMap.get("ui_Error").show();
        });
    }

    public void initStages(){
        try {
            new JFXPanel();
            initPre();
            initMenu();
        }catch (Exception e){
            new errorManager("UI 생성중 에러", e.getMessage());
            e.printStackTrace();
        }
    }
}
