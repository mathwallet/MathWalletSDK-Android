@@ -1,132 +0,0 @@
README_en.md


# MathWalletSDK-Android
#### Android SDK based on SimpleWallet protocol
#### Supports public chains : ETH,EOS,EOSFORCE etc.
# How to install？
#### 1.Copy mathwalletlib-release.aar in the libs of Sample to your app libs
#### 2.Add in app's build.gradle:
```
repositories {
      flatDir {
            dirs 'libs'
      }
}
```
#### 3.Add implement in build.gradle of app
```
 implementation(name: 'mathwalletlib-release', ext: 'aar')
```
#### 4.copy the following code to your AndroidManifest.xml
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
Set a custom host and scheme
# How to use？
#### 1.Login
```java
 MathWalletLogin mathWalletLogin = new MathWalletLogin();
 mathWalletLogin.setBlockchain("eosio"); //blockchain（eosio、eosforce、ethereum etc）
 mathWalletLogin.setAction("login"); //login
 mathWalletLogin.setDappName("Math Wallet testing - SDK"); //dapp name
 mathWalletLogin.setDappIcon("http://medishares.oss-cn-hongkong.aliyuncs.com/logo/mds-parity.png");//dapp logo Url
 mathWalletLogin.setUuID(UUID.randomUUID().toString()); //only uuid to verify login
 mathWalletLogin.setLoginUrl(null);  //app login setLoginUrl is null!!!!!!!
 mathWalletLogin.setExpired(1538100593l); //login expire time
 mathWalletLogin.setLoginMemo("testing"); //notes（optional）
 mathWalletLogin.setCallback("customscheme://customhost?action=login"); //callback，scheme and host must be the same as the RouterActivity in xml
 MathWalletManager.getInstance().requestLogin(this, mathWalletLogin, new MathWalletCallBack() {
      @Override
      public void callBack(Map<String, String> params, String uriString) {
            // callBack accountName or address, and permission if is eos blockchain
            LogUtil.e(TAG, new JSONObject(params).toString());
            LogUtil.e(TAG, uriString);
      }
 });
```
#### 2.Transfer
```java
 MathWalletPay mathWalletPay = new MathWalletPay();
 mathWalletPay.setBlockchain("eosio"); //blockchain（eosio、eosforce、ethereum etc.）
 mathWalletPay.setAction("transfer"); //pay
 mathWalletPay.setDappName("MathWallet testing SDK"); //dapp name
 mathWalletPay.setDappIcon("http://medishares.oss-cn-hongkong.aliyuncs.com/logo/mds-parity.png");//dapp logo Url
 mathWalletPay.setFrom("account11"); //payer
 mathWalletPay.setTo("account12"); //receiver
 mathWalletPay.setAmount("1.2345"); //amount
 mathWalletPay.setContract("eosio.token"); //contract address
 mathWalletPay.setSymbol("EOS"); //token name
 mathWalletPay.setPrecision(4); //amount accuracy
 mathWalletPay.setDappData("MathWallet dapp testing");//memo or data
 mathWalletPay.setDesc("payment testing"); //transaction info
 mathWalletPay.setExpired(1538100593l); //transaction expire time
 mathWalletPay.setCallback("customscheme://customhost?action=transfer"); //allback，scheme and host must be the same as the RouterActivity in xml
 MathWalletManager.getInstance().requestPay(this, mathWalletPay, new MathWalletCallBack() {
      @Override
      public void callBack(Map<String, String> params, String uriString) {
            LogUtil.e(TAG, new JSONObject(params).toString());
            LogUtil.e(TAG, uriString);
      }
 });
```
#### 3.Custom Action
```java
  MathWalletAction mathWalletAction = new MathWalletAction();
  mathWalletAction.setBlockchain("eosio");       //blockchain
  mathWalletAction.setAction("transaction");        //transaction
  mathWalletAction.setDappName("麦子钱包测试SDK"); //dapp name
  mathWalletAction.setDappIcon("http://medishares.oss-cn-hongkong.aliyuncs.com/logo/mds-parity.png");//dapp url
  mathWalletAction.setFrom("eosmedishares");         //from
  mathWalletAction.setDappData("麦子钱包dapp测试");//memo or data
  mathWalletAction.setDesc("这是ACTION测试");        //desc
  mathWalletAction.setExpired(1538100593l);      //expired
  ArrayList<Action> actions = new ArrayList<>();
  Action action = new Action();
  action.setCode("eosio");
  action.setAction("delegatebw");
  action.setBinargs("a09865fe4c9c0761c0a6eb6c1acda891010000000000000004454f5300000000010000000000000004454f530000000000");
  actions.add(action);
  mathWalletAction.setCallback("customscheme://customhost?action=transaction");   //callback
     MathWalletManager.getInstance().requestAction(this, mathWalletAction, new MathWalletCallBack() {
         @Override
         public void callBack(Map<String, String> params, String uriString) {
             LogUtil.e(TAG, new JSONObject(params).toString());
             LogUtil.e(TAG, uriString);
         }
     });
```  
#### 4.open DappUrl
```java
  MathWalletUrl mathWalletUrl = new MathWalletUrl("http://eosrand.io/?ref=maoguangxing");     //dappUrl
  mathWalletUrl.setBlockchain("eosio");   //公链标识  eosio,eosforce,ethereum
  MathWalletManager.getInstance().reqeustOpenUrl(this, mathWalletUrl);
```  

#### 5.sign message
```java
        MathWalletSignMessage mathWalletSignMessage = new MathWalletSignMessage();
        mathWalletSignMessage.setCallback("customscheme://customhost?action=signMessage");
        mathWalletSignMessage.setIsHash(false);
        mathWalletSignMessage.setMessage("这是一条测试数据");
        mathWalletSignMessage.setBlockchain("eosio");
        mathWalletSignMessage.setDappIcon("http://medishares.oss-cn-hongkong.aliyuncs.com/logo/mds-parity.png");//dapp图标Url
        mathWalletSignMessage.setDappName("这是测试的签名数据");
        MathWalletManager.getInstance().requestSignMessage(this, mathWalletSignMessage, new MathWalletCallBack() {
            @Override
            public void callBack(Map<String, String> params, String uriString) {
                LogUtil.e(TAG, new JSONObject(params).toString());
                LogUtil.e(TAG, uriString);
            }
        });
```  