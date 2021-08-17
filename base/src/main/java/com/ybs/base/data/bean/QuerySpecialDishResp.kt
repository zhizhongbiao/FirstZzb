package com.ybs.base.data.bean

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


class QuerySpecialDishResp() : Parcelable {
    //    id (string, optional): id ,
    //    merchantId (string, optional): 商户id ,
    //    specialDishImgUrl (string, optional): 特色菜图片 ,
    //    specialDishPrice (string, optional): 特色菜价格 ,
    //    specialDishQRCode (string, optional): 特色菜二维码绑定的url ,
    //    specialDishTitle (string, optional): 特色菜名称
    var id: String? = null
    var merchantId: String? = null
    var specialDishImgUrl: String? = null
    var specialDishPrice: String? = null
    var specialDishQRCode: String? = null
    var specialDishTitle: String? = null
    var isActive //是否推荐 true：推荐 false：不推荐
            = false

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        merchantId = parcel.readString()
        specialDishImgUrl = parcel.readString()
        specialDishPrice = parcel.readString()
        specialDishQRCode = parcel.readString()
        specialDishTitle = parcel.readString()
        isActive = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(merchantId)
        parcel.writeString(specialDishImgUrl)
        parcel.writeString(specialDishPrice)
        parcel.writeString(specialDishQRCode)
        parcel.writeString(specialDishTitle)
        parcel.writeByte(if (isActive) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuerySpecialDishResp> {
        override fun createFromParcel(parcel: Parcel): QuerySpecialDishResp {
            return QuerySpecialDishResp(parcel)
        }

        override fun newArray(size: Int): Array<QuerySpecialDishResp?> {
            return arrayOfNulls(size)
        }
    }
}