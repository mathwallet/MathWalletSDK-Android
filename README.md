# MathWalletSDK-Android
#### 基于SimpleWallet协议的Android SDK
#### 支持公链 ETH,EOS,EOSFORCE 等

[English Readme](https://github.com/MediShares/MathWalletSDK-Android/blob/master/README_en.md)


# 如何安装 ？
#### 1.复制 Sample 中 libs 下的 mathwalletlib-release.aar 到你的 app libs 下
#### 2.在app的build.gradle中添加
```
repositories {
    flatDir {
        dirs 'libs'
    }
}
```  
#### 3.在app的build.gradle中添加依赖
 ```
    implementation(name: 'mathwalletlib-release', ext: 'aar')
```
#### 4.添加依赖库 implementation 'com.alibaba:fastjson:1.1.70.android'，如果已添加则无需重复添加
#### 5.复制下面的代码到你的AndroidManifest.xml
```xml
       <activity android:name="com.medishares.mathwalletlib.RouterActivity">
            <intent-filter>
                <data
                    android:host="customhost"
                    android:scheme="customscheme"/>
                <data/>

                <category android:name="android.intent.category.DEFAULT"/>
                <action android:name="android.intent.action.VIEW"/>
                <category android:name="android.intent.category.BROWSABLE"/>

            </intent-filter>
        </activity>
```
设置自定义的host以及scheme

# 如何使用 ？
#### 1.登录
```java
  MathWalletLogin mathWalletLogin = new MathWalletLogin();
  mathWalletLogin.setBlockchain("eosio");                  //公链标识（eosio、eosforce、ethereum等）
  mathWalletLogin.setAction("login");                      //登录
  mathWalletLogin.setDappName("麦子钱包测试—SDK");           //dapp名字
  mathWalletLogin.setDappIcon("http://medishares.oss-cn-hongkong.aliyuncs.com/logo/mds-parity.png");//dapp图标Url
  mathWalletLogin.setUuID(UUID.randomUUID().toString());   //登录验证唯一标识
  mathWalletLogin.setLoginUrl("http://www.medishares.org");//service生成，用于接收此次登录验证的URL
  mathWalletLogin.setExpired(1538100593l);                            //登录过期时间
  mathWalletLogin.setLoginMemo("这是登录测试");               //备注信息，可选
  mathWalletLogin.setCallback("customscheme://customhost?action=login");                //回调，scheme和host务必和RouterActivity在xml中设置的相同
  MathWalletManager.getInstance().requestLogin(this, mathWalletLogin, new MathWalletCallBack() {
      @Override
      public void callBack(Map<String, String> params, String uriString) {
          LogUtil.e(TAG, new JSONObject(params).toString());
          LogUtil.e(TAG, uriString);
      }
  });
```

#### 2.转账
```java
 MathWalletPay mathWalletPay = new MathWalletPay();
 mathWalletPay.setBlockchain("eosio");       //公链标识（eosio、eosforce、ethereum等）
 mathWalletPay.setAction("transfer");        //支付
 mathWalletPay.setDappName("麦子钱包测试SDK"); //dapp名字
 mathWalletPay.setDappIcon("http://medishares.oss-cn-hongkong.aliyuncs.com/logo/mds-parity.png");//dapp图标Url
 mathWalletPay.setFrom("account11");         //付款人
 mathWalletPay.setTo("account12");           //收款人
 mathWalletPay.setAmount("1.2345");          //转账数量
 mathWalletPay.setContract("eosio.token");   //合约地址
 mathWalletPay.setSymbol("EOS");             //token名称
 mathWalletPay.setPrecision(4);              //转账token精度
 mathWalletPay.setDappData("麦子钱包dapp测试");//memo or data
 mathWalletPay.setDesc("这是支付测试");        //交易的说明信息
 mathWalletPay.setExpired(1538100593l);            //交易过期时间
 mathWalletPay.setCallback("customscheme://customhost?action=transfer");   //回调，scheme和host务必和RouterActivity在xml中设置的相同
 MathWalletManager.getInstance().requestPay(this, mathWalletPay, new MathWalletCallBack() {
     @Override
     public void callBack(Map<String, String> params, String uriString) {
        LogUtil.e(TAG, new JSONObject(params).toString());
        LogUtil.e(TAG, uriString);
     }
 });
```  
#### 3.自定义的Action
```java
  MathWalletAction mathWalletAction = new MathWalletAction();
  mathWalletAction.setBlockchain("eosio");       //公链标识
  mathWalletAction.setAction("transaction");        //支付
  mathWalletAction.setDappName("麦子钱包测试SDK"); //dapp名字
  mathWalletAction.setDappIcon("http://medishares.oss-cn-hongkong.aliyuncs.com/logo/mds-parity.png");//dapp图标Url
  mathWalletAction.setFrom("eosmedishares");         //付款人
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
```  
#### 4.Dapp跳转
```java
  MathWalletUrl mathWalletUrl = new MathWalletUrl("http://eosrand.io/?ref=maoguangxing");     //dappUrl
  mathWalletUrl.setBlockchain("eosio");   //公链标识  eosio,eosforce,ethereum
  MathWalletManager.getInstance().reqeustOpenUrl(this, mathWalletUrl);
```  
