package com.android.thesmartdataloader.core.loggers;

import android.util.Log;

import com.android.thesmartdataloader.core.interfaces.ILogger;

public class DebugLogger implements ILogger {
    String TAG = "DebugLogger";
    @Override
    public void log(String message) {
        Log.d(TAG, message);
    }
}
