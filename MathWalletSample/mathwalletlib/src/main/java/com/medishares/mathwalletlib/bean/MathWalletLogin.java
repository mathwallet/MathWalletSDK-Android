package com.medishares.mathwalletlib.bean;

public class MathWalletLogin extends BaseMathWallet {

    private String uuID;
    private String action = "login";
    private long   expired;
    private String callback;
    private String loginUrl;
    private String loginMemo;

    public String getUuID() {
        return uuID;
    }

    public void setUuID(String uuID) {
        this.uuID = uuID;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    public long getExpired() {
        return expired;
    }

    public void setExpired(long expired) {
        this.expired = expired;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLoginMemo() {
        return loginMemo;
    }

    public void setLoginMemo(String loginMemo) {
        this.loginMemo = loginMemo;
    }
}
