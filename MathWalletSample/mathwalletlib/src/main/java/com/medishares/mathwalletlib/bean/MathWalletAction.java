package com.medishares.mathwalletlib.bean;

import java.util.List;

public class MathWalletAction extends MathWalletPay {

    private String action = "transaction";
    //eos ,eosforce 自定义合约转账参数，eth的转账在dappData中添加
    private List<Action> actions;         // 合约array

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

}
