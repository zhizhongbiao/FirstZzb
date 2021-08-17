package com.ybs.ndklib;

public class JniUtils {
    static {
        System.loadLibrary("ybs-sign-tool");
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String signData(String data, String productModel);
}
