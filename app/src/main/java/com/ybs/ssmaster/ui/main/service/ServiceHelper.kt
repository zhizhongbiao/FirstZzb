package com.ybs.ssmaster.ui.main.service

import android.text.TextUtils
import com.ybs.base.data.bean.QueryRestaurantCommResp
import com.ybs.base.data.bean.TemplateConfig
import com.ybs.ssmaster.ui.main.FROM_MANUAL
import com.ybs.ssmaster.ui.main.MainAct

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.ui.main.service
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/28 11:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/28 11:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */


fun toTemplatePage(act: MainAct, data: QueryRestaurantCommResp, from: Int = FROM_MANUAL) {
    if (data.services?.isNotEmpty() == true) {

    } else if (data.templateConfig != null) {

        val config: TemplateConfig = data.templateConfig

        if (!TextUtils.isEmpty(config.qrcode)) {

        } else if (!TextUtils.isEmpty(config.fullImage)) {

            return
        } else if (!TextUtils.isEmpty(config.standardImage) && !TextUtils.isEmpty(config.displayText)) {

            return
        } else if (!TextUtils.isEmpty(config.displayText)) {

            return
        } else if (!TextUtils.isEmpty(config.ttsText) && from == FROM_MANUAL) {

        }


        if (data.watchConfig != null) {
            if (data.code.contains("DPYX_SERVICE_ADD_APRON")) { //todo 加围裙修改数量需特殊处理

            } else {

            }
        }
    }
}
