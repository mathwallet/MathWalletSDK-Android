package com.medishares.mathwalletlib.bean;

import java.util.HashMap;

public class MathTrxParameter {

    private HashMap<String, Object> value;
    private String                  type_url;

    public HashMap<String, Object> getValue() {
        return value;
    }

    public void setValue(HashMap<String, Object> value) {
        this.value = value;
    }

    public String getType_url() {
        return type_url;
    }

    public void setType_url(String type_url) {
        this.type_url = type_url;
    }
}
