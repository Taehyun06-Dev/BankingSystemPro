package com.TaehyunDev.utils.managers;

import com.TaehyunDev.Ui.stageManager;
import lombok.Data;

@Data
public class errorManager {

    public static String errorMsg, errorDetail;

    public errorManager(String errorMsg, String errorDetail){
        errorManager.errorMsg = errorMsg;
        errorManager.errorDetail = errorDetail;
        new stageManager().showError();
    }
}
