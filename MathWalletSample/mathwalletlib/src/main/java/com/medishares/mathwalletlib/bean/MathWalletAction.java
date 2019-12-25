package com.medishares.mathwalletlib.bean;

import java.util.List;

public class MathWalletAction extends BaseMathWallet {

    private String from;            // 付款人的EOS账号，可选
    private String desc;            // 交易的说明信息，钱包在付款UI展示给用户，最长不要超过128个字节，可选
    private long   expired;
    private String callback;        // 用户完成操作后，钱包回调拉起dapp移动端的回调URL,如https://abc.com?action=login&qrcID=123，可选
    private String action = "transaction";

    //eos ,eosforce
    private List<Action> actions;         // 合约array

    //eth
    private String to;
    private String amount;
    private String dappData;        // 由dapp生成的业务参数信息，需要钱包在转账时附加在memo中发出去，格式为:k1=v1&k2=v2，可选

    //trx
    private MathTrxContract contract;

    public MathTrxContract getContract() {
        return contract;
    }

    public void setContract(MathTrxContract contract) {
        this.contract = contract;
    }

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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDappData() {
        return dappData;
    }

    public void setDappData(String dappData) {
        this.dappData = dappData;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
