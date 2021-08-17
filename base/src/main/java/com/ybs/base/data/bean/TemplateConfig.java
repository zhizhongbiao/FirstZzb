package com.ybs.base.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Android Studio.
 * User: qym
 * Date: 2020/12/18
 * Time: 11:28
 */
public class TemplateConfig implements Parcelable {

    public String displayText;
    public String fullImage;
    public String qrcode;
    public String standardImage;
    public String standardImageTitle;
    public String ttsText;

    protected TemplateConfig(Parcel in) {
        displayText = in.readString();
        fullImage = in.readString();
        qrcode = in.readString();
        standardImage = in.readString();
        standardImageTitle = in.readString();
        ttsText = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(displayText);
        dest.writeString(fullImage);
        dest.writeString(qrcode);
        dest.writeString(standardImage);
        dest.writeString(standardImageTitle);
        dest.writeString(ttsText);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TemplateConfig> CREATOR = new Creator<TemplateConfig>() {
        @Override
        public TemplateConfig createFromParcel(Parcel in) {
            return new TemplateConfig(in);
        }

        @Override
        public TemplateConfig[] newArray(int size) {
            return new TemplateConfig[size];
        }
    };


    @Override
    public String toString() {
        return "TemplateConfig{" +
                "displayText='" + displayText + '\'' +
                ", fullImage='" + fullImage + '\'' +
                ", qrcode='" + qrcode + '\'' +
                ", standardImage='" + standardImage + '\'' +
                ", standardImageTitle='" + standardImageTitle + '\'' +
                ", ttsText='" + ttsText + '\'' +
                '}';
    }
}
