package com.ybs.base.data.params;



public class GetAccessTokenReq {
//    deviceTag (string, optional): 实体唯一标识 ,
//    productionModelSn (string, optional): 产品型号SN ,
//    signToken (string, optional): 加密token ,
//    timestamp (integer, optional): 时间戳

    public String deviceTag;//实体唯一标识
    public String productionModelSn;//产品型号SN
    public String signToken;//加密token
    public long timestamp;//时间戳

    @Override
    public String toString() {
        return "GetAccessTokenReq{" +
                "deviceTag='" + deviceTag + '\'' +
                ", productionModelSn='" + productionModelSn + '\'' +
                ", signToken='" + signToken + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
