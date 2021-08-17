package com.ybs.ndklib;

public class DenoiseUtil {
    static {
        System.loadLibrary("ybs-denoise-tool");
    }

    public native int vadOpen();

    public native DenoiseResult vadRun(short[] shorts, int length);

    public native void vadClose();
}
