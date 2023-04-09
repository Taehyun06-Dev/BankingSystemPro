package com.TaehyunDev.Ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class stageManager {

    private static Map<String, Stage> stageMap = new HashMap<>();

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

}
