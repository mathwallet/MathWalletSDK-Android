package com.medishares.mathwalletlib;

import android.content.Context;

import com.medishares.mathwalletlib.bean.MathWalletAction;
import com.medishares.mathwalletlib.bean.MathWalletLogin;
import com.medishares.mathwalletlib.bean.MathWalletPay;
import com.medishares.mathwalletlib.bean.MathWalletUrl;

public interface MathWalletApi {

    void requestLogin(Context context, MathWalletLogin mathWalletLogin, MathWalletCallBack callBack);

    void requestPay(Context context, MathWalletPay mathWalletPay, MathWalletCallBack callBack);

    void requestAction(Context context, MathWalletAction mathWalletAction, MathWalletCallBack callBack);

    void reqeustOpenUrl(Context context, MathWalletUrl mathWalletUrl);
}
