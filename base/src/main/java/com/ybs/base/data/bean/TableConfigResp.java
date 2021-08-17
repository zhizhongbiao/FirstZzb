package com.ybs.base.data.bean;

/**
 * Created by ouyangguozhao
 */

// "tableArea": "string",
//         "tableAreaId": "string",
//         "tableId": "string",
//         "tableNumber": "string"

public class TableConfigResp {
    public String tableArea;
    public String tableAreaId;
    public String tableId;
    public String tableNumber;

    @Override
    public String toString() {
        return "GetSourceConfigResp{" +
                "tableArea='" + tableArea + '\'' +
                ", tableAreaId='" + tableAreaId + '\'' +
                ", tableId='" + tableId + '\'' +
                ", tableNumber='" + tableNumber + '\'' +
                '}';
    }
}
