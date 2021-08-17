package com.ybs.ndklib;

public class DenoiseResult {

    public short[] sigOut;

    public int endPoint;

    // sig 输入信号 size 输入信号长度
    // sigOut 输出信号
    // endPoint 是否尾点
    // 返回 0 sigOut 没有输出 ；返回 > 0 singOut 为声音数据；返回 <0 sigOut 为静音数据
    public int result;

    public void setInfo(int result, short[] sigOut, int endPoint) {
        this.result = result;
        this.sigOut = sigOut;
        this.endPoint = endPoint;
    }

}
