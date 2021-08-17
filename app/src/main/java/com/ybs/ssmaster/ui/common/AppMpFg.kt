package com.ybs.ssmaster.ui.common

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ybs.base.view.fg.BaseMpFg
import com.ybs.reslib.databinding.FgMpBinding
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
abstract class AppMpFg : BaseMpFg<FgMpBinding, MainAct>() {


    private val vm by lazy {
        retrieveActVm(MainVm::class.java)
    }

    override fun getVv() = binding?.vv!!

    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FgMpBinding.inflate(inflater, container, false)





    var tts: String? = null



    override fun onCompletion(mp: MediaPlayer?) {
        dismiss()
    }

}