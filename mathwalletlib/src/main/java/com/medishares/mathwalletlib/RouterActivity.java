package com.medishares.mathwalletlib;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class RouterActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            Uri uri = intent.getData();
            MathWalletManager.getInstance().uriCallBack(uri);
        }
        this.finish();
    }
}
