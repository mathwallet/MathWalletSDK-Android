package com.medishares.mathwalletlib.util;

import android.util.Log;

public class LogUtil {

    public static boolean isDebug;

    private LogUtil() {
    }

    public static void d(String tag, String log) {
        if (isDebug) {
            Log.d(tag, log);
        }
    }

    public static void e(String tag, String log) {
        if (isDebug) {
            Log.e(tag, log);
        }
    }

    public static void i(String tag, String log) {
        if (isDebug) {
            Log.i(tag, log);
        }
    }
}
