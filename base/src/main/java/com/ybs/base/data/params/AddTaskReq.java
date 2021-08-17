package com.ybs.base.data.params;

/**
 * Created by ouyangguozhao
 */

public class AddTaskReq {
//    code (string, optional),
//    id (string, optional),
//    merchantId (string),
//    sn (string),
//    tableNumber (string)
    public String code;
    public String codeValue;
    public String merchantId;
    public String sourceSn;
    public int serviceFrom;//任务来源方式 1平板 2语音
    public String serviceId;
    public String tableId;
    public String tableNumber;
    public String taskTitle;
    public String tableArea;
    public String tableAreaId;
}
