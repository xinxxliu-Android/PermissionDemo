package com.example.permissiondemo.util;

import android.os.Handler;
import android.os.Looper;

public class ThreadUtil {
    public static boolean isRunOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static Handler mainHandler() {
        return new Handler(Looper.getMainLooper());
    }
}
