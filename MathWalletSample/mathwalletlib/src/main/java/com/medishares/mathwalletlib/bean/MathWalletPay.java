package com.medishares.mathwalletlib.bean;

public class MathWalletPay extends BaseMathWallet {

    private String action = "transfer";
    private String from;            // 付款人的EOS账号，可选
    private String to;              // 收款人的EOS账号，必须
    private String amount;          // 转账数量，必须
    private String contract;        // 转账的token所属的contract账号名，必须
    private String symbol;          // 转账的token名称，必须
    private int    precision;       // 转账的token的精度，小数点后面的位数，必须
    private long   expired;
    private String dappData;        // 由dapp生成的业务参数信息，需要钱包在转账时附加在memo中发出去，格式为:k1=v1&k2=v2，可选
    private String desc;            // 交易的说明信息，钱包在付款UI展示给用户，最长不要超过128个字节，可选
    private String callback;        // 用户完成操作后，钱包回调拉起dapp移动端的回调URL,如https://abc.com?action=login&qrcID=123，可选

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public long getExpired() {
        return expired;
    }

    public void setExpired(long expired) {
        this.expired = expired;
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

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
