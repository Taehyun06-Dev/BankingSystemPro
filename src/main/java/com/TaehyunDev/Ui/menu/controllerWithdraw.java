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

public class controllerWithdraw implements Initializable {
    @FXML
    private Button btt_go;

    @FXML
    private ImageView img_home;

    @FXML
    private TextField txt_account;

    @FXML
    private TextField txt_money;

    @FXML
    private TextArea txt_result;

    @FXML
    private PasswordField txt_pass;

    private userData data = new userData();

    private void setIntOnlyField(TextField[] fieldList) {
        for (TextField field : fieldList) {
            field.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }
    }

    private void clear() {
        txt_account.setText("");
        txt_money.setText("");
        txt_pass.setText("");
    }

    private void reloadAutoComplete() {
        Set<Integer> tempSet = data.getDataMap().keySet();
        tempSet.remove(-999);
        TextFields.bindAutoCompletion(txt_account, tempSet);
    }

    private void notify(String text){
        txt_result.setText(text);
    }

    private void withdraw() {
        if (txt_account.getText().length() >= 1 && txt_money.getText().length() >= 1 && txt_pass.getText().length() >= 1) {
            Integer money = Integer.parseInt(txt_money.getText());
            Integer account = Integer.parseInt(txt_account.getText());
            if (money <= 0) {
                notify("올바른 금액을 입력하십시오.");
                return;
            }
            if (!data.getDataMap().containsKey(account)) {
                notify("올바른 계좌번호를 입력하십시오.");
                return;
            }
            userAccount tempAccount = data.getAccount(account);
            if(tempAccount.getAccountBalance() < money){
                notify("잔액이 부족합니다.");
                return;
            }
            if(tempAccount.getAccountPass() != Integer.parseInt(txt_pass.getText())){
                notify("비밀번호가 틀립니다.");
                return;
            }
            tempAccount.setAccountBalance(tempAccount.getAccountBalance() - money);
            data.registerDataByAccount(account, tempAccount);
            notify("계좌번호: " + account + " 출금액: " + money + "원\n고객명: "
                    + tempAccount.getUserName() + " 거래후 잔액: " + tempAccount.getAccountBalance()+"원");
            clear();
            reloadAutoComplete();
            new threadManager().saveData();
        }

    }

    private void initPre() {
        txt_result.setEditable(false);
        txt_pass.setStyle("-fx-echo-char: -A");
        img_home.setOnMouseClicked(e -> {
            txt_result.setText("");
            new stageManager().switchStage("ui_Withdraw", "ui_Main");
        });

        btt_go.setOnMouseClicked(e -> withdraw());
        setIntOnlyField(new TextField[]{txt_money, txt_account, txt_pass});
        reloadAutoComplete();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPre();
    }
}
