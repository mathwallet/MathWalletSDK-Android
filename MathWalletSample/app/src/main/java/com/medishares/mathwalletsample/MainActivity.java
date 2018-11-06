package com.medishares.mathwalletsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.medishares.mathwalletlib.MathWalletCallBack;
import com.medishares.mathwalletlib.MathWalletManager;
import com.medishares.mathwalletlib.bean.Action;
import com.medishares.mathwalletlib.bean.MathWalletAction;
import com.medishares.mathwalletlib.bean.MathWalletLogin;
import com.medishares.mathwalletlib.bean.MathWalletPay;
import com.medishares.mathwalletlib.bean.MathWalletUrl;
import com.medishares.mathwalletlib.util.LogUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatButton loginBtn = findViewById(R.id.login_btn);
        AppCompatButton payBtn = findViewById(R.id.pay_btn);
        AppCompatButton actionBtn = findViewById(R.id.action_btn);
        AppCompatButton openDappBtn = findViewById(R.id.openDapp_btn);
        loginBtn.setOnClickListener(this);
        payBtn.setOnClickListener(this);
        actionBtn.setOnClickListener(this);
        openDappBtn.setOnClickListener(this);
        LogUtil.isDebug = true;     //打开log日志
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:        //登录
                login();
                break;
            case R.id.pay_btn:          //支付
                pay();
                break;
            case R.id.action_btn:       //自定义action
                action();
                break;
            case R.id.openDapp_btn:     //openDapp
                openDapp();
                break;
        }
    }

    private void openDapp() {
        MathWalletUrl mathWalletUrl = new MathWalletUrl("http://eosrand.io/?ref=maoguangxing");     //dappUrl
        mathWalletUrl.setBlockchain("ethereum");   //公链标识
        MathWalletManager.getInstance().reqeustOpenUrl(this, mathWalletUrl);
    }

    private void action() {
        MathWalletAction mathWalletAction = new MathWalletAction();
        mathWalletAction.setBlockchain("eosio");       //公链标识
        mathWalletAction.setAction("transaction");        //支付
        mathWalletAction.setDappName("麦子钱包测试SDK"); //dapp名字
        mathWalletAction.setDappIcon("http://medishares.oss-cn-hongkong.aliyuncs.com/logo/mds-parity.png");//dapp图标Url
        mathWalletAction.setFrom("0x5EFd3dAd23Ad3ef9C40caeaCB64CDC0e44957E5D");         //付款人
        mathWalletAction.setDappData("麦子钱包dapp测试");//memo or data
        mathWalletAction.setDesc("这是ACTION测试");        //交易的说明信息
        mathWalletAction.setExpired(1538100593l);      //交易过期时间
        ArrayList<Action> actions = new ArrayList<>();
        Action action = new Action();
        action.setCode("eosio");
        action.setAction("delegatebw");
        action.setBinargs("a09865fe4c9c0761c0a6eb6c1acda891010000000000000004454f5300000000010000000000000004454f530000000000");
        actions.add(action);
        mathWalletAction.setCallback("customscheme://customhost?action=transaction");   //回调，scheme和host务必和RouterActivity在xml中设置的相同
        MathWalletManager.getInstance().requestAction(this, mathWalletAction, new MathWalletCallBack() {
            @Override
            public void callBack(Map<String, String> params, String uriString) {
                LogUtil.e(TAG, new JSONObject(params).toString());
                LogUtil.e(TAG, uriString);
            }
        });
    }

    private void pay() {
        MathWalletPay mathWalletPay = new MathWalletPay();
        mathWalletPay.setBlockchain("eosio");       //公链标识
        mathWalletPay.setAction("transfer");        //支付
        mathWalletPay.setDappName("麦子钱包测试SDK"); //dapp名字
        mathWalletPay.setDappIcon("http://medishares.oss-cn-hongkong.aliyuncs.com/logo/mds-parity.png");//dapp图标Url
        mathWalletPay.setFrom("");         //付款人
        mathWalletPay.setTo("maoguangxing");           //收款人
        mathWalletPay.setAmount("0.0001");          //转账数量
        mathWalletPay.setContract("eosio.token");   //合约地址
        mathWalletPay.setSymbol("EOS");             //token名称
        mathWalletPay.setPrecision(4);              //转账token精度
        mathWalletPay.setDappData("麦子钱包dapp测试");//memo or data
        mathWalletPay.setDesc("这是支付测试");        //交易的说明信息
        mathWalletPay.setExpired(1538100593l);      //交易过期时间
        mathWalletPay.setCallback("customscheme://customhost?action=transfer");   //回调，scheme和host务必和RouterActivity在xml中设置的相同
        MathWalletManager.getInstance().requestPay(this, mathWalletPay, new MathWalletCallBack() {
            @Override
            public void callBack(Map<String, String> params, String uriString) {
                LogUtil.e(TAG, new JSONObject(params).toString());
                LogUtil.e(TAG, uriString);
            }
        });
    }

    private void login() {
        MathWalletLogin mathWalletLogin = new MathWalletLogin();
        mathWalletLogin.setBlockchain("ethereum");                  //公链标识
        mathWalletLogin.setAction("login");                      //登录
        mathWalletLogin.setDappName("麦子钱包测试—SDK");           //dapp名字
        mathWalletLogin.setDappIcon("http://medishares.oss-cn-hongkong.aliyuncs.com/logo/mds-parity.png");//dapp图标Url
        mathWalletLogin.setUuID(UUID.randomUUID().toString());   //登录验证唯一标识
        mathWalletLogin.setLoginUrl("http://www.medishares.org");//service生成，用于接收此次登录验证的URL
        mathWalletLogin.setExpired(1538100593l);                 //登录过期时间
        mathWalletLogin.setLoginMemo("这是登录测试");              //备注信息，可选
        mathWalletLogin.setCallback("customscheme://customhost?action=login");                //回调，scheme和host务必和RouterActivity在xml中设置的相同
        MathWalletManager.getInstance().requestLogin(this, mathWalletLogin, new MathWalletCallBack() {
            @Override
            public void callBack(Map<String, String> params, String uriString) {
                LogUtil.e(TAG, new JSONObject(params).toString());
                LogUtil.e(TAG, uriString);
            }
        });
    }
}
