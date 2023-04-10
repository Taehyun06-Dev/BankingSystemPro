package com.TaehyunDev.Data;

import java.util.HashMap;
import java.util.Map;

public class userData{

    private static Map<Integer, userAccount> userDataMap = new HashMap<>();

    public void registerData(Integer accountNum, Integer accountPass, Integer accountBalance, String userName){
        userDataMap.put(accountNum, new userAccount(accountPass, accountBalance, userName));
    }

    public void registerDataByAccount(Integer accountNum, userAccount account){
        userDataMap.put(accountNum, account);
    }

    public userAccount getAccount(Integer accountNum){
        if(!userDataMap.containsKey(accountNum)){
            return null;
        }
        return userDataMap.get(accountNum);
    }


    public void setTotalMap(Map<Integer, userAccount> dataMap){
        userDataMap = dataMap;
    }

    public Map<Integer, userAccount> getDataMap(){
        return userDataMap;
    }

}
