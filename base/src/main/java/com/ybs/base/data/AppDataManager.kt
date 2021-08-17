package com.ybs.base.data

import androidx.lifecycle.MutableLiveData
import com.ybs.base.BaseApp
import com.ybs.base.data.bean.GetAccessTokenResp
import com.ybs.base.data.bean.QuerySpecialDishResp
import com.ybs.base.data.bean.QueryVoiceGuideResp
import com.ybs.base.data.bean.TableConfigResp
import com.ybs.base.util.SysUtil
import com.ybs.base.util.getBoolean

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/21 10:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/21 10:39
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */


const val KEY_HAS_SCANNED = "hasScanned"
const val PM_SN = "XB-FF-1"
const val APP_CODE = "FF_ANDROID_APP"

class AppDataManager private constructor() {

    companion object {
        val inst = AppDataManager()
    }

    var guidelineList: MutableList<QueryVoiceGuideResp> = mutableListOf()
    val appEvent: MutableLiveData<MsgEvent> by lazy { MutableLiveData() }

    val hasScannedQRCode: MutableLiveData<Boolean> by lazy {
        MutableLiveData(getBoolean(KEY_HAS_SCANNED, false))
    }

    var orderQRCodeUrl: String? = null
    var allFoodList: MutableList<QuerySpecialDishResp> = mutableListOf()
    var accessTokenResp: GetAccessTokenResp? = null
    var tableConfigResp: TableConfigResp? = null

    var token: String = "token is not initialized"

    val  deviceMac: String by lazy {
        SysUtil.getMacAddress(BaseApp.app)
    }
}