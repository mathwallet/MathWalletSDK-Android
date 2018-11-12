package com.medishares.mathwalletlib.bean;

public class MathWalletUrl extends BaseMathWallet {

    private String dappUrl;             //dapp链接
    private String action = "openUrl";  //打开链接
    private String callback;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    public MathWalletUrl(String dappUrl) {
        this.dappUrl = dappUrl;
    }

    public String getDappUrl() {
        return dappUrl;
    }

    public void setDappUrl(String dappUrl) {
        this.dappUrl = dappUrl;
    }
}
