package com.TaehyunDev.Data;

import lombok.Data;

@Data
public class fileData {

    private Boolean stat;
    private Boolean isValid;

    public fileData(Boolean stat, Boolean isValid){
        this.stat = stat;
        this.isValid = isValid;
    }
}
