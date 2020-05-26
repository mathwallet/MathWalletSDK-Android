package com.medishares.mathwalletsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.medishares.mathwalletlib.MathWalletCallBack;
import com.medishares.mathwalletlib.MathWalletManager;
import com.medishares.mathwalletlib.bean.Action;
import com.medishares.mathwalletlib.bean.MathTrxContract;
import com.medishares.mathwalletlib.bean.MathTrxParameter;
import com.medishares.mathwalletlib.bean.MathWalletAction;
import com.medishares.mathwalletlib.bean.MathWalletLogin;
import com.medishares.mathwalletlib.bean.MathWalletPay;
import com.medishares.mathwalletlib.bean.MathWalletSignMessage;
import com.medishares.mathwalletlib.bean.MathWalletUrl;
import com.medishares.mathwalletlib.util.LogUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.medishares.mathwalletsample.Chains.CHAIN_EOS;
import static com.medishares.mathwalletsample.Chains.CHAIN_EOSFORCE;
import static com.medishares.mathwalletsample.Chains.CHAIN_ETH;
import static com.medishares.mathwalletsample.Chains.CHAIN_TRX;

public class SimpleWalletActivity extends AppCompatActivity
        implements View.OnClickListener {

    public static final String TAG = "SimpleWalletActivityTag";

    private String mBlockchain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplewallet);
        mBlockchain = getIntent().getStringExtra("BLOCKCHAIN");
        AppCompatButton loginBtn = findViewById(R.id.login_btn);
        AppCompatButton payBtn = findViewById(R.id.pay_btn);
        AppCompatButton actionBtn = findViewById(R.id.action_btn);
        AppCompatButton openDappBtn = findViewById(R.id.openDapp_btn);
        AppCompatButton signMessageBtn = findViewById(R.id.signMessage_btn);
        if (CHAIN_TRX.equals(mBlockchain)) {
            payBtn.setVisibility(View.GONE);
        } else {
            payBtn.setVisibility(View.VISIBLE);
        }
        loginBtn.setOnClickListener(this);
        payBtn.setOnClickListener(this);
        actionBtn.setOnClickListener(this);
        openDappBtn.setOnClickListener(this);
        signMessageBtn.setOnClickListener(this);
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
            case R.id.signMessage_btn:     //openDapp
                signMessage();
                break;
        }
    }


    private void signMessage() {
        MathWalletSignMessage mathWalletSignMessage = new MathWalletSignMessage();
        mathWalletSignMessage.setCallback("customscheme://customhost?action=signMessage");
        mathWalletSignMessage.setDappIcon("http://medishares.oss-cn-hongkong.aliyuncs.com/logo/mds-parity.png");//dapp图标Url
        mathWalletSignMessage.setDappName("这是测试的签名数据");

                mathWalletSignMessage.setIsHash(false);
                mathWalletSignMessage.setMessage("这是一条测试数据");
        //
        //hash 为true，则 message 需要先处理
                            mathWalletSignMessage.setIsHash(false);
                            mathWalletSignMessage.setMessage(SHA256Util.getSHA256StrJava("这是一条测试数据"));

        if (CHAIN_EOS.equals(mBlockchain)) {
            mathWalletSignMessage.setBlockchain("eosio");                  //公链标识

        } else if (CHAIN_ETH.equals(mBlockchain)) {
            mathWalletSignMessage.setBlockchain("ethereum");

        } else if (CHAIN_TRX.equals(mBlockchain)) {
            mathWalletSignMessage.setBlockchain("tron");

        } else if (CHAIN_EOSFORCE.equals(mBlockchain)) {
            mathWalletSignMessage.setBlockchain("eosforce");

        }

        MathWalletManager.getInstance().requestSignMessage(this, mathWalletSignMessage, new MathWalletCallBack() {
            @Override
            public void callBack(Map<String, String> params, String uriString) {
                LogUtil.e(TAG, new JSONObject(params).toString());
                LogUtil.e(TAG, uriString);
            }
        });
    }


    private void action() {
        MathWalletAction mathWalletAction = new MathWalletAction();
        mathWalletAction.setCallback("customscheme://customhost?action=transaction");   //回调，scheme和host务必和RouterActivity在xml中设置的相同
        mathWalletAction.setAction("transaction");        //支付
        mathWalletAction.setDappName("麦子钱包测试SDK"); //dapp名字
        mathWalletAction.setDappIcon("http://medishares.oss-cn-hongkong.aliyuncs.com/logo/mds-parity.png");//dapp图标Url
        mathWalletAction.setDesc("这是波场测试测试");        //交易的说明信息
        mathWalletAction.setExpired(1538100593l);      //交易过期时间
        mathWalletAction.setDappData("麦子钱包dapp测试");//memo or data

        if (CHAIN_EOS.equals(mBlockchain)) {
            mathWalletAction.setBlockchain("eosio");                  //公链标识

            ArrayList<Action> actions = new ArrayList<>();
            Action action = new Action();
            action.setAction("buyrambytes");
            action.setCode("eosio");
            action.setBinargs("a09865fe4c9c0761c0a6eb6c1acda891800c0000");          //可以通过abi_json_to_bin rpc接口转换
            actions.add(action);
            mathWalletAction.setActions(actions);

        } else if (CHAIN_ETH.equals(mBlockchain)) {                     //参考 eth 的 pay 方式

        } else if (CHAIN_TRX.equals(mBlockchain)) {
            mathWalletAction.setBlockchain("tron");
            mathWalletAction.setFrom("");         //付款人
            MathTrxContract mathTrxContract = new MathTrxContract();
            MathTrxParameter mathTrxParameter = new MathTrxParameter();
            HashMap<String, Object> valueMap = new HashMap<>();
            valueMap.put("data", "7365870b0000000000000000000000000000000000000000000000000000000000000060");
            valueMap.put("owner_address", "TWXNtL6rHGyk2xeVR3QqEN9QGKfgyRTeU2");
            valueMap.put("contract_address", "TWXNtL6rHGyk2xeVR3QqEN9QGKfgyRTeU2");
            valueMap.put("call_value", 10000000);
            mathTrxParameter.setValue(valueMap);
            mathTrxParameter.setType_url("type.googleapis.com/protocol.TriggerSmartContract");
            mathTrxContract.setParameter(mathTrxParameter);
            mathTrxContract.setType("TriggerSmartContract");
            mathWalletAction.setContract(mathTrxContract);

        } else if (CHAIN_EOSFORCE.equals(mBlockchain)) {
            mathWalletAction.setBlockchain("eosforce");

            ArrayList<Action> actions = new ArrayList<>();
            Action action = new Action();
            action.setAction("buyrambytes");
            action.setCode("eosio");
            action.setBinargs("a09865fe4c9c0761c0a6eb6c1acda891800c0000");          //和eos的基本类似，
            actions.add(action);
            mathWalletAction.setActions(actions);

        }

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
        mathWalletPay.setAction("transfer");        //支付
        mathWalletPay.setDappName("Pay Test by MathWallet"); //dapp名字
        mathWalletPay.setDappIcon("http://medishares.oss-cn-hongkong.aliyuncs.com/logo/mds-parity.png");//dapp图标Url
        mathWalletPay.setDesc("这是支付测试");        //交易的说明信息
        mathWalletPay.setExpired(1577072794l);      //交易过期时间
        mathWalletPay.setCallback("customscheme://customhost?action=transfer");   //回调，scheme和host务必和RouterActivity在xml中设置的相同

        if (CHAIN_EOS.equals(mBlockchain)) {
            mathWalletPay.setBlockchain("eosio");                  //公链标识
            mathWalletPay.setFrom("");                             //付款人,选填，
            mathWalletPay.setTo("maoguangxing");                   //收款人
            mathWalletPay.setAmount("0.0001");                     //转账数量


            //eos转账
            mathWalletPay.setSymbol("EOS");                             //token名称
            mathWalletPay.setContract("eosio.token");                         //合约地址
            mathWalletPay.setPrecision(4);                              //转账token精度
            mathWalletPay.setDappData("eos transfer memo");             //memo，选填

            //token转账
            //            mathWalletPay.setSymbol("NDX");                     //token名称
            //            mathWalletPay.setContract("newdexissuer");          //合约地址
            //            mathWalletPay.setPrecision(4);                      //转账token精度
            //            mathWalletPay.setDappData("ndx transfer memo");        //memo，选填

        } else if (CHAIN_ETH.equals(mBlockchain)) {
            mathWalletPay.setBlockchain("ethereum");
            mathWalletPay.setFrom("");
            mathWalletPay.setPrecision(18);                              //精度始终是eth的精度
            mathWalletPay.setSymbol("ETH");                             //token名称


            //eth转账
//            mathWalletPay.setAmount("0.0001");                     //转账数量
//            mathWalletPay.setTo("0xd13b6B5f5EDc411d510b14B9CDFDEbf8c0D42Ba5");  //收款地址
//            mathWalletPay.setDappData("");                              //eth转账无需填写data

            //erc20转账
            mathWalletPay.setAmount("0.0001");                               //转账0Eth
            mathWalletPay.setTo("0x08d967bb0134f2d07f7cfb6e246680c53927dd30");  //收款地址 为合约地址！！！
            mathWalletPay.setDappData("0xa9059cbb000000000000000000000000e93381fb4c4f14bda253907b18fad305d799241a0000000000000000000000000000000000000000000000559906e61975cc0000");                              //data 为执行erc20转账的data transfer(address _to, uint256 _value)

        } else if (CHAIN_EOSFORCE.equals(mBlockchain)) {
            mathWalletPay.setBlockchain("eosforce");
            mathWalletPay.setFrom("");                             //付款人,选填，
            mathWalletPay.setTo("maoguangxing");                   //收款人
            mathWalletPay.setAmount("0.0001");                     //转账数量

            //eosforce转账
            mathWalletPay.setSymbol("EOS");                             //token名称
            mathWalletPay.setContract("eosio");                         //合约地址
            mathWalletPay.setPrecision(4);                              //转账token精度
            mathWalletPay.setDappData("eosforce transfer memo");             //memo，选填

            //token转账
            //            mathWalletPay.setSymbol("NDX");                     //token名称
            //            mathWalletPay.setContract("newdexissuer");          //合约地址
            //            mathWalletPay.setPrecision(4);                      //转账token精度
            //            mathWalletPay.setDappData("ndx transfer memo");        //memo，选填
        }

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
        mathWalletLogin.setAction("login");                      //登录
        mathWalletLogin.setDappName("Login Test by MathWallet");           //dapp名字
        mathWalletLogin.setUuID(UUID.randomUUID().toString());   //登录验证唯一标识
        mathWalletLogin.setDappIcon("http://medishares.oss-cn-hongkong.aliyuncs.com/logo/mds-parity.png");//dapp图标Url
        mathWalletLogin.setExpired(1538100593l);                 //登录过期时间
        mathWalletLogin.setCallback("customscheme://customhost?action=login");                //回调，scheme和host务必和RouterActivity在xml中设置的相同
        mathWalletLogin.setLoginUrl(null);                      //app跳转无需填写
        mathWalletLogin.setLoginMemo("Login Test memo");              //备注信息，可选


        if (CHAIN_EOS.equals(mBlockchain)) {
            mathWalletLogin.setBlockchain("eosio");                  //公链标识

        } else if (CHAIN_ETH.equals(mBlockchain)) {
            mathWalletLogin.setBlockchain("ethereum");

        } else if (CHAIN_TRX.equals(mBlockchain)) {
            mathWalletLogin.setBlockchain("tron");

        } else if (CHAIN_EOSFORCE.equals(mBlockchain)) {
            mathWalletLogin.setBlockchain("eosforce");

        }

        MathWalletManager.getInstance().requestLogin(this, mathWalletLogin, new MathWalletCallBack() {
            @Override
            public void callBack(Map<String, String> params, String uriString) {
                LogUtil.e(TAG, new JSONObject(params).toString());
                LogUtil.e(TAG, uriString);
            }
        });
    }


    private void openDapp() {
        MathWalletUrl mathWalletUrl = new MathWalletUrl("http://www.maiziqianbao.net/");     //dappUrl

        if (CHAIN_EOS.equals(mBlockchain)) {
            mathWalletUrl.setBlockchain("eosio");                  //公链标识

        } else if (CHAIN_ETH.equals(mBlockchain)) {
            mathWalletUrl.setBlockchain("ethereum");

        } else if (CHAIN_TRX.equals(mBlockchain)) {
            mathWalletUrl.setBlockchain("tron");

        } else if (CHAIN_EOSFORCE.equals(mBlockchain)) {
            mathWalletUrl.setBlockchain("eosforce");

        }

        mathWalletUrl.setCallback("customscheme://customhost?action=openurl");
        MathWalletManager.getInstance().reqeustOpenUrl(this, mathWalletUrl, new MathWalletCallBack() {
            @Override
            public void callBack(Map<String, String> params, String uriString) {
                LogUtil.e(TAG, new JSONObject(params).toString());
                LogUtil.e(TAG, uriString);
            }
        });
    }
}
