package com.ybs.base.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Android Studio.
 * User: qym
 * Date: 2020/12/18
 * Time: 11:28
 */
public class WatchConfig implements Parcelable {
    public String code;
    public String codeValue;

    protected WatchConfig(Parcel in) {
        code = in.readString();
        codeValue = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(codeValue);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WatchConfig> CREATOR = new Creator<WatchConfig>() {
        @Override
        public WatchConfig createFromParcel(Parcel in) {
            return new WatchConfig(in);
        }

        @Override
        public WatchConfig[] newArray(int size) {
            return new WatchConfig[size];
        }
    };


    @Override
    public String toString() {
        return "WatchConfig{" +
                "code='" + code + '\'' +
                ", codeValue='" + codeValue + '\'' +
                '}';
    }
}
