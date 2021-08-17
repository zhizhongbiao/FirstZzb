package com.ybs.base.data.bean;

//        applicationCode (string, optional): 应用码值 ,
//        applicationName (string, optional): 应用名 ,
//        isEnforce (integer, optional): 是否强制升级（0:否 1:是） ,
//        isOnline (integer, optional): 是否线上版本（0:否 1:是） ,
//        releaseDate (string, optional): 发布日期 ,
//        updatePackageUrl (string, optional): 升级包url ,
//        versionCode (integer, optional): 应用版本号 ,
//        versionDescription (string, optional): 应用版本描述 ,
//        versionName (string, optional): 应用版本名

import android.os.Parcel;
import android.os.Parcelable;

public class UpgradeResp implements Parcelable {
    public String applicationCode;
    public String applicationName;
    public int versionCode;
    public String versionDescription;
    public String versionName;
    public String updatePackageUrl;
    public int isOnline;
    public int isEnforce;
    public String releaseDate;

    protected UpgradeResp(Parcel in) {
        applicationCode = in.readString();
        applicationName = in.readString();
        versionCode = in.readInt();
        versionDescription = in.readString();
        versionName = in.readString();
        updatePackageUrl = in.readString();
        isOnline = in.readInt();
        isEnforce = in.readInt();
        releaseDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(applicationCode);
        dest.writeString(applicationName);
        dest.writeInt(versionCode);
        dest.writeString(versionDescription);
        dest.writeString(versionName);
        dest.writeString(updatePackageUrl);
        dest.writeInt(isOnline);
        dest.writeInt(isEnforce);
        dest.writeString(releaseDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UpgradeResp> CREATOR = new Creator<UpgradeResp>() {
        @Override
        public UpgradeResp createFromParcel(Parcel in) {
            return new UpgradeResp(in);
        }

        @Override
        public UpgradeResp[] newArray(int size) {
            return new UpgradeResp[size];
        }
    };
}
