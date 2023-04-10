package com.TaehyunDev.Ui.menu;

import com.TaehyunDev.Data.userAccount;
import com.TaehyunDev.Data.userData;
import com.TaehyunDev.Ui.stageManager;
import com.TaehyunDev.utils.managers.threadManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.controlsfx.control.textfield.TextFields;

public class controllerSetting implements Initializable {

    @FXML
    private Button btt_go;

    @FXML
    private TextField txt_account;

    @FXML
    private TextField txt_balance;

    @FXML
    private TextField txt_pass;

    @FXML
    private TextArea txt_result;

    @FXML
    private TextField txt_user;

    @FXML
    private ImageView img_home;

    private userData data = new userData();

    private void setIntOnlyField(TextField[] fieldList){
        for(TextField field : fieldList) {
            field.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }
    }

    private void clear(){
        txt_account.setText("");
        txt_pass.setText("");
        txt_balance.setText("");
        txt_user.setText("");
    }

    private void reloadAutoComplete(){
        Set<Integer> tempSet = data.getDataMap().keySet();
        tempSet.remove(-999);
        TextFields.bindAutoCompletion(txt_account, tempSet);
    }

    private void notify(String text){
        txt_result.setText(text);
    }

    private void register(){
        if(txt_account.getText().length() >= 1 && txt_pass.getText().length() >= 1 && txt_balance.getText().length() >= 1 && txt_user.getText().length() >= 1){
            Integer account = Integer.parseInt(txt_account.getText());
            Integer pass = Integer.parseInt(txt_pass.getText());
            Integer balance = Integer.parseInt(txt_balance.getText());
            String user = txt_user.getText();
            userAccount accountTemp = new userAccount(pass, balance, user);
            data.registerDataByAccount(account, accountTemp);
            notify("계좌번호: "+account+" 비밀번호: "+pass+"\n잔액: "+balance+" 고객명: "+user+"\n등록되었습니다.");
            clear();
            reloadAutoComplete();
            new threadManager().saveData();
        }
    }

    private void initPre(){
        txt_result.setEditable(false);
        img_home.setOnMouseClicked(e->{
            clear();
            txt_result.setText("");
            new stageManager().switchStage("ui_Setting", "ui_Main");
        });

        btt_go.setOnMouseClicked(e->register());
        setIntOnlyField(new TextField[]{txt_account, txt_balance, txt_pass});

        reloadAutoComplete();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPre();
    }
}
