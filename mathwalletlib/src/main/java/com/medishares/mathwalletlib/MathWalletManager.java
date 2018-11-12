package com.medishares.mathwalletlib;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.medishares.mathwalletlib.bean.MathWalletAction;
import com.medishares.mathwalletlib.bean.MathWalletLogin;
import com.medishares.mathwalletlib.bean.MathWalletPay;
import com.medishares.mathwalletlib.bean.MathWalletUrl;
import com.medishares.mathwalletlib.util.LogUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class MathWalletManager implements MathWalletApi {

    public static final String TAG = "MathWalletManager";

    private MathWalletCallBack mMathWalletCallBack;

    private MathWalletManager() {
    }

    public static final MathWalletManager getInstance() {
        return MathWalletManager.Holder.INSTANCE;
    }

    public void uriCallBack(Uri uri) {
        if (uri != null) {
            String scheme = uri.getScheme();
            if (TextUtils.isEmpty(scheme)) {
                LogUtil.e(TAG, "scheme is null");
                return;
            }
            String query = uri.getQuery();
            if (!TextUtils.isEmpty(query)) {
                LogUtil.e(TAG, "callBack->" + query);
                String[] params = query.split("&");
                HashMap<String, String> paramsMap = new HashMap<>();
                for (int i = 0; i < params.length; i++) {
                    String param = params[i];
                    String[] key_value = param.split("=");
                    if (key_value.length == 2) {
                        paramsMap.put(key_value[0], key_value[1]);
                    } else {
                        LogUtil.e(TAG, "key or value is empty");
                    }
                }
                if (this.mMathWalletCallBack != null) {
                    mMathWalletCallBack.callBack(paramsMap, uri.toString());
                } else {
                    LogUtil.e(TAG, "callback is null");
                }
            } else {
                LogUtil.e(TAG, "query is empty");
            }
        } else {
            LogUtil.e(TAG, "uri is null");
        }
    }

    private static final class Holder {
        private static final MathWalletManager INSTANCE = new MathWalletManager();

        private Holder() {
        }
    }

    public MathWalletCallBack getMathWalletCallBack() {
        return mMathWalletCallBack;
    }

    public void setMathWalletCallBack(MathWalletCallBack mathWalletCallBack) {
        mMathWalletCallBack = mathWalletCallBack;
    }

    @Override
    public void requestLogin(Context context, MathWalletLogin mathWalletLogin, MathWalletCallBack callBack) {
        if (context != null && mathWalletLogin != null && callBack != null) {
            this.mMathWalletCallBack = callBack;
            String loginJson = JSONObject.toJSONString(mathWalletLogin);
            requestUri(context, loginJson);
        } else {
            LogUtil.e(TAG, "context or mathWalletLogin is null");
        }
    }

    @Override
    public void requestPay(Context context, MathWalletPay mathWalletPay, MathWalletCallBack callBack) {
        if (context != null && mathWalletPay != null && callBack != null) {
            this.mMathWalletCallBack = callBack;
            String payJson = JSONObject.toJSONString(mathWalletPay);
            requestUri(context, payJson);
        } else {
            LogUtil.e(TAG, "context or mathWalletPay is null");
        }
    }

    @Override
    public void requestAction(Context context, MathWalletAction mathWalletAction, MathWalletCallBack callBack) {
        if (context != null && mathWalletAction != null && callBack != null) {
            this.mMathWalletCallBack = callBack;
            String actionJson = JSONObject.toJSONString(mathWalletAction);
            requestUri(context, actionJson);
        } else {
            LogUtil.e(TAG, "context or mathWalletAction is null");
        }
    }

    @Override
    public void reqeustOpenUrl(Context context, MathWalletUrl mathWalletUrl, MathWalletCallBack callBack) {
        if (context != null && mathWalletUrl != null) {
            if (callBack != null)
                this.mMathWalletCallBack = callBack;
            String actionJson = JSONObject.toJSONString(mathWalletUrl);
            requestUri(context, actionJson);
        } else {
            LogUtil.e(TAG, "context or mathWalletUrl is null");
        }
    }

    private void requestUri(Context context, String json) {
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append(Constants.MATHWALLET_PARAM_URL);
        try {
            String encode = URLEncoder.encode(json, "utf-8");
            String uriString = uriBuilder.append(encode).toString();
            LogUtil.e(TAG, "uriString->" + uriString);
            Uri uri = Uri.parse(uriString);
            MathWalletHelper.openMathWallet(context, uri);
        } catch (UnsupportedEncodingException e) {
            LogUtil.e(TAG, "UnsupportedEncodingException->" + e.getLocalizedMessage());
        }
    }


}
