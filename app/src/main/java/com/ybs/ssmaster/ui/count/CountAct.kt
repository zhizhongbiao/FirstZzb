package com.ybs.ssmaster.ui.count

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import com.ybs.reslib.databinding.ActCountBinding
import com.ybs.ssmaster.R
import com.ybs.ssmaster.ui.common.AppBaseAct
import com.ybs.ssmaster.ui.scan.ScanFg


class CountAct : AppBaseAct<ActCountBinding, CountVm>() {


    companion object {

        fun startAct(ctx: Context, id: String) {
            ctx.startActivity(Intent(ctx, CountAct::class.java).apply {
                putExtra(KEY_ID, id)
            })
        }
    }

    override fun isFullScreen()=false

    override fun getVmClz() = CountVm::class.java

    override fun getLayoutBinding() = ActCountBinding.inflate(layoutInflater)

    override fun initView() {
        isLoadingNeed=false
        val id = intent?.getStringExtra(KEY_ID) ?: "id is null"
        vm.initHu()

        supportFragmentManager?.apply {
           fg= ScanFg.newInst()
            beginTransaction().replace(R.id.fgContainer,fg!!)
                .commitNowAllowingStateLoss()
        }

    }

    var fg:ScanFg?=null

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == 139 || keyCode == 280) {
            if (event.repeatCount == 0) {
                   fg?.handleBtnClick()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        fg=null
    }

}