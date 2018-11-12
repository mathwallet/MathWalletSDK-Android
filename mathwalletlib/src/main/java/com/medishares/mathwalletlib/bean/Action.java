package com.medishares.mathwalletlib.bean;

public class Action {

    private String code;
    private String action;
    private String binargs;

    public Action() {
    }

    public Action(String code, String action, String binargs) {
        this.code = code;
        this.action = action;
        this.binargs = binargs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBinargs() {
        return binargs;
    }

    public void setBinargs(String binargs) {
        this.binargs = binargs;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
