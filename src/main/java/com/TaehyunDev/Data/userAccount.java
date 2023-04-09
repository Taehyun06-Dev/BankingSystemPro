package com.TaehyunDev.Data;

import lombok.Data;

import java.io.Serializable;

@Data
public class userAccount implements Serializable {

    public userAccount(Integer accountPass, Integer accountBalance, String userName){
        this.accountPass = accountPass;
        this.accountBalance = accountBalance;
        this.userName = userName;
    }

    private Integer accountPass;
    private Integer accountBalance;
    private String userName;


}
