package com.ybs.base.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class QueryRestaurantCommResp implements Parcelable {
//    code (string, optional): 服务标识 ,
//    id (string, optional),
//    imageUrl (string, optional): 服务图片 ,
//    imageUrlHighlight (string, optional): 服务图片-高亮 ,
//    merchantId (string, optional): 商户ID ,
//    name (string, optional): 服务名称 ,

    @Override
    public String toString() {
        return "QueryRestaurantCommResp{" +
                "id='" + id + '\'' +
                ", thumbnailImage='" + thumbnailImage + '\'' +
                ", thumbnailImageTitle='" + thumbnailImageTitle + '\'' +
                ", code='" + code + '\'' +
                ", codeValue='" + codeValue + '\'' +
                ", services=" + services +
                ", templateConfig=" + templateConfig +
                ", watchConfig=" + watchConfig +
                '}';
    }
//    parentId (string, optional): 上级ID ,
//    qrcode (string, optional): 二维码 ,
//    services (Array[FoodServiceRespDTO], optional): 二级服务

    public String id;
    public String thumbnailImage;
    public String thumbnailImageTitle;
    public String code;
    public String codeValue;
    public List<QueryRestaurantCommResp> services;
    public TemplateConfig templateConfig;
    public WatchConfig watchConfig;

    protected QueryRestaurantCommResp(Parcel in) {
        id = in.readString();
        thumbnailImage = in.readString();
        thumbnailImageTitle = in.readString();
        code = in.readString();
        codeValue = in.readString();
        services = in.createTypedArrayList(QueryRestaurantCommResp.CREATOR);
        templateConfig = in.readParcelable(TemplateConfig.class.getClassLoader());
        watchConfig = in.readParcelable(WatchConfig.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(thumbnailImage);
        dest.writeString(thumbnailImageTitle);
        dest.writeString(code);
        dest.writeString(codeValue);
        dest.writeTypedList(services);
        dest.writeParcelable(templateConfig, flags);
        dest.writeParcelable(watchConfig, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QueryRestaurantCommResp> CREATOR = new Creator<QueryRestaurantCommResp>() {
        @Override
        public QueryRestaurantCommResp createFromParcel(Parcel in) {
            return new QueryRestaurantCommResp(in);
        }

        @Override
        public QueryRestaurantCommResp[] newArray(int size) {
            return new QueryRestaurantCommResp[size];
        }
    };



}
