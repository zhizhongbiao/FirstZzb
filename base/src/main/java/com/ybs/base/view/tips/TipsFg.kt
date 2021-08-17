package com.ybs.base.view.tips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ybs.base.databinding.DlgTipsBinding
import com.ybs.base.view.fg.BaseDialogFg
import e
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool.box
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/14 19:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/14 19:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */


class TipsFg : BaseDialogFg<DlgTipsBinding>() {

    private var job: Job? = null

    var onSureClickCb: (() -> Unit)? = null

    override fun getLayoutBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DlgTipsBinding.inflate(inflater, container, false)

    override fun initView(args: Bundle?) {
        binding!!.btnSure.setOnClickListener {
            dismiss()
            onSureClickCb?.invoke()
        }
    }

    override fun onResume() {
        super.onResume()
        updateUIState()
    }

    private var msg = "提示"


    fun setMsg(msgs: String) = this.apply {
        msg = msgs
        if (isResumed) {
            updateUIState()
        }
    }

    private fun updateUIState() = this.apply {
        e("msg = $msg")

//        job = lifecycleScope.launch(Dispatchers.Main) {
//            delayTimeInSecond(3000)
//            dismiss()
//        }
        binding!!.tvMsg.text = msg
    }

    private suspend fun delayTimeInSecond(s: Long) = withContext(Dispatchers.IO) {
        delay(s)
    }

    override fun getW() = 1500
    override fun getH() = 660

}