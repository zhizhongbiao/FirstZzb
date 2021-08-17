package com.ybs.ssmaster.ui.count

import android.R
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.Gravity
import android.view.KeyEvent
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ToastUtils
import com.google.android.material.tabs.TabLayoutMediator
import com.ybs.reslib.databinding.ActCountBinding
import com.ybs.ssmaster.ui.common.AppBaseAct
import com.ybs.ssmaster.ui.main.MainVm
import com.ybs.ssmaster.ui.main.counter.VpAdapt


class CountAct : AppBaseAct<ActCountBinding, CountVm>() {


    private val activeColor: Int = Color.BLUE
    private val normalColor: Int = Color.parseColor("#666666")

    private val activeSize = 20f
    private val normalSize = 20f

    private val mediator: TabLayoutMediator by lazy {
        TabLayoutMediator(binding.tb, binding.vp) { tab, pos ->
            val tabView = TextView(this@CountAct)

            val states = arrayOfNulls<IntArray>(2)
            states[0] = intArrayOf(R.attr.state_selected)
            states[1] = intArrayOf()

            val colors = intArrayOf(activeColor, normalColor)
            val colorStateList = ColorStateList(states, colors)
            tabView.text = tabs[pos]
            tabView.textSize = normalSize
            tabView.setTextColor(colorStateList)
            tabView.gravity = Gravity.CENTER
            tab.customView = tabView
        }
    }

    private val fgs = mutableListOf<Fragment>()
    val tabs = arrayOf("当前盘点", "盘点计划")

    companion object {

        fun startAct(ctx: Context, id: String) {
            ctx.startActivity(Intent(ctx, CountAct::class.java).apply {
                putExtra(KEY_ID, id)
            })
        }
    }


    override fun getVmClz() = CountVm::class.java

    override fun getLayoutBinding() = ActCountBinding.inflate(layoutInflater)

    override fun initView() {
        val id = intent?.getStringExtra(KEY_ID) ?: "id is null"
        vm.setCountId(id)



        vm.getPossessList(
            id,
            vm.filter!!.value!!.no,
            vm.filter!!.value!!.loc,
            vm.filter!!.value!!.state,
            vm.filter!!.value!!.name
        ).observe(this@CountAct, {
            if (it?.data?.isOk() == true) {

            } else {
                ToastUtils.showLong(it.data?.msg ?: "获取数据失败！")
            }
        })


        fgs.add(ScanFg.newInst())
        fgs.add(PlanFg.newInst(id))
        binding.vp.adapter = VpAdapt(fgs, supportFragmentManager, lifecycle)
        mediator.attach()

        binding.ivBack.setOnClickListener {
            finish()
        }

        vm.initHu()

        binding.btnPost.setOnClickListener {
//            if (vm.req.checkDetailId.isEmpty()) {
//                ToastUtils.showLong("没有可以提交的未盘资产，请先扫描吧")
//                return@setOnClickListener
//            }

            vm.postCountedRecord().observe(this, {
                if (it?.data?.isOk() == true) {
//                    ToastUtils.showLong("提交数据成功！")
                    val refreshData = MainVm.refreshData
                    refreshData.postValue(refreshData.value!!.inc())
                    finish()
                } else {
                    ToastUtils.showLong(it.data?.msg ?: "提交数据失败！")
                }

            })
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == 139 || keyCode == 280) {
            if (event.repeatCount == 0) {
                if (binding.vp.currentItem == 0) {
                    (fgs[0] as ScanFg).handleBtnClick()
                }
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}