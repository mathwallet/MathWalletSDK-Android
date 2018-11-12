package com.medishares.mathwalletlib;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.medishares.mathwalletlib.util.Utils;

public class MathWalletHelper {

    public static void openMathWallet(Context context, Uri uri) {
        if (context != null && uri != null) {
            boolean isAppInstall = Utils.isAppInstall(context, Constants.MATHWALLET_PACKAGE_NAME);
            if (isAppInstall) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(context, Constants.MATHWALLET_INSTALL_TOAST, Toast.LENGTH_SHORT).show();
                Uri downLoadUri = Uri.parse(Constants.MATHWALLET_DOWNLOAD_URL);
                Intent intent = new Intent(Intent.ACTION_VIEW, downLoadUri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
    }
}
