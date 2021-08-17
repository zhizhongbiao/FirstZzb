package com.ybs.base.http.download

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base.http.download
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/6/1 16:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/1 16:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
data class DownloadStateBean(val totalLength: Long, val byteLoaded: Long, val tag: String) :
    Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(totalLength)
        parcel.writeLong(byteLoaded)
        parcel.writeString(tag)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "DownloadStateBean(totalLength=$totalLength, byteLoaded=$byteLoaded, tag='$tag')"
    }

    companion object CREATOR : Parcelable.Creator<DownloadStateBean> {
        override fun createFromParcel(parcel: Parcel): DownloadStateBean {
            return DownloadStateBean(parcel)
        }

        override fun newArray(size: Int): Array<DownloadStateBean?> {
            return arrayOfNulls(size)
        }
    }
}
