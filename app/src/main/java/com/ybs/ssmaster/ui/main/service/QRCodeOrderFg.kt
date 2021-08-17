package com.ybs.ssmaster.ui.main.service

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ybs.reslib.databinding.FgQrcodeOrderBinding
import com.ybs.ssmaster.ui.common.AppBaseFg
import com.ybs.ssmaster.ui.main.MainAct
import com.ybs.ssmaster.ui.main.MainVm

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.ui.main.process
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/25 19:36
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/25 19:36
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

const val KEY_QRCODE_DATA = "keyQRCodeData"
const val KEY_FROM = "keyFrom"
const val FROM_RECOMMEND = 1
const val FROM_SERVICE = 2

class QRCodeOrderFg : AppBaseFg<FgQrcodeOrderBinding,MainVm, MainAct>() {

    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FgQrcodeOrderBinding.inflate(inflater, container, false)

    override fun initView() {


    }






    override fun getVmClz()=MainVm::class.java
}