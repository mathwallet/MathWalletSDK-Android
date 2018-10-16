package com.medishares.mathwalletlib.bean;

public class BaseMathWallet {

    private String protocol ="SimpleWallet";
    private String version  = "1.0";
    private String dappName;
    private String dappIcon;
    private String blockchain;
    private String action;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDappName() {
        return dappName;
    }

    public void setDappName(String dappName) {
        this.dappName = dappName;
    }

    public String getDappIcon() {
        return dappIcon;
    }

    public void setDappIcon(String dappIcon) {
        this.dappIcon = dappIcon;
    }

    public String getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(String blockchain) {
        this.blockchain = blockchain;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
