package com.medishares.mathwalletlib.bean;

public class MathWalletSignMessage extends BaseMathWallet {

    private String  action = "signMessage";
    private String  callback;
    private boolean isHash;
    private String  message;

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public boolean getIsHash() {
        return isHash;
    }

    public void setIsHash(boolean isHash) {
        this.isHash = isHash;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
