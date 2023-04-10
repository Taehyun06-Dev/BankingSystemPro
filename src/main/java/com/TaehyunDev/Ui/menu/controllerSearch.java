package com.TaehyunDev.Ui.menu;

import com.TaehyunDev.Data.userAccount;
import com.TaehyunDev.Data.userData;
import com.TaehyunDev.Ui.stageManager;
import com.TaehyunDev.utils.managers.threadManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class controllerSearch implements Initializable {

    @FXML
    private Button btt_go;

    @FXML
    private ImageView img_home;

    @FXML
    private TextField txt_account;

    @FXML
    private PasswordField txt_pass;

    @FXML
    private TextArea txt_result;
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
    }

    private void reloadAutoComplete(){
        Set<Integer> tempSet = data.getDataMap().keySet();
        tempSet.remove(-999);
        TextFields.bindAutoCompletion(txt_account, tempSet);
    }

    private void notify(String text){
        txt_result.setText(text);
    }

    private void search(){
        if(txt_account.getText().length() >= 1 && txt_pass.getText().length() >= 1){
            Integer account = Integer.parseInt(txt_account.getText());
            if(!data.getDataMap().containsKey(account)){
                notify("올바른 계좌번호를 입력하십시오.");
                return;
            }
            userAccount tempAccount = data.getAccount(account);
            if(tempAccount.getAccountPass() != Integer.parseInt(txt_pass.getText())){
                notify("비밀번호가 틀립니다.");
                return;
            }
            notify("계좌번호: "+account+" 고객명: "
                    +tempAccount.getUserName()+"\n잔액: "+tempAccount.getAccountBalance()+"원");
            clear();
            reloadAutoComplete();
        }
    }

    private void initPre(){
        txt_result.setEditable(false);
        img_home.setOnMouseClicked(e-> {
            clear();
            txt_result.setText("");
            new stageManager().switchStage("ui_Search", "ui_Main");
        });

        btt_go.setOnMouseClicked(e->search());
        setIntOnlyField(new TextField[]{txt_pass, txt_account});

        reloadAutoComplete();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPre();
    }
}
