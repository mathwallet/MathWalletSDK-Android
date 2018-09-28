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
 mathWalletLogin.setLoginUrl("http://www.medishares.org");//serviceissued，URL to receive the current login verification
 mathWalletLogin.setExpired(1538100593l); //login expire time
 mathWalletLogin.setLoginMemo("testing"); //notes（optional）
 mathWalletLogin.setCallback("customscheme://customhost?action=login"); //callback，scheme and host must be the same as the RouterActivity in xml
 MathWalletManager.getInstance().requestLogin(this, mathWalletLogin, new MathWalletCallBack() {
 @Override
 public void callBack(Map<String, String> params, String uriString) {
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
