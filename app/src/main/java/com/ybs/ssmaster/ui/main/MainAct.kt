package com.ybs.ssmaster.ui.main


import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.ybs.base.util.clearAllSpData
import com.ybs.reslib.databinding.ActMainBinding
import com.ybs.ssmaster.ui.common.AppBaseAct
import com.ybs.ssmaster.ui.main.counter.CountedFg
import com.ybs.ssmaster.ui.main.counter.ToCountFg
import com.ybs.ssmaster.ui.main.counter.VpAdapt
import com.ybs.ssmaster.ui.main.recommend.CountFilterDlg
import com.ybs.ssmaster.ui.main.service.ServiceConfirmDlg
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter


class MainAct : AppBaseAct<ActMainBinding, MainVm>() {

    private val fgs = mutableListOf<Fragment>()
    val tabs = arrayOf("待盘点", "已盘点")

    companion object {
        fun startAct(ctx: Context) {
            ctx.startActivity(Intent(ctx, MainAct::class.java))
        }
    }


    override fun getVmClz() = MainVm::class.java

    override fun getLayoutBinding() = ActMainBinding.inflate(layoutInflater)

    override fun initView() {
        fgs.add(ToCountFg.newInst())
        fgs.add(CountedFg.newInst())
        binding.vp.adapter = VpAdapt(fgs, supportFragmentManager, lifecycle)
        mediator.attach()

        binding.ivBack.setOnClickListener {
            showDlg("确定退出登录？") {
                clearAllSpData()
                finish()
            }
        }
        MainVm.refreshData.observe(this, {
            if (it != 0) {
                vm.filter.postValue(vm.filter.value!!.copy())
            }
        })

        vm.countNum.observe(this, {
            binding?.tvCountNum.visibility = if (it == 0)
                View.GONE else View.VISIBLE

            binding?.tvCountNum.text = "$it"
        })


        binding.btnFilter.setOnClickListener {
            CountFilterDlg.newInst().showDlg(supportFragmentManager)
        }

        binding.fl.adapter = a

        vm.filter.observe(this, {
            if (it == null || it.isEmpty()) {
                binding.fl.visibility = View.GONE
                return@observe
            }

            binding.fl.visibility = View.VISIBLE
            tags.apply {
                clear()
                add(it.d)
                add(it.y)
                add(it.p)
                add(it.n)
            }
            a.notifyDataChanged()

        })
    }

    private val a by lazy {
        object : TagAdapter<String>(tags) {
            override fun getView(parent: FlowLayout?, position: Int, s: String?): View {

                val tv = layoutInflater.inflate(
                    com.ybs.ssmaster.R.layout.layout_tv,
                    binding.fl, false
                ) as TextView
                tv.text = s
                return tv
            }

        }
    }


    private val tags: ArrayList<String> = arrayListOf()

    private val activeColor: Int = Color.BLUE
    private val normalColor: Int = Color.parseColor("#666666")

    private val activeSize = 20f
    private val normalSize = 20f

    private val mediator: TabLayoutMediator by lazy {
        TabLayoutMediator(binding.tb, binding.vp) { tab, pos ->
            val tabView = TextView(this@MainAct)

            val states = arrayOfNulls<IntArray>(2)
            states[0] = intArrayOf(android.R.attr.state_selected)
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


    fun showDlg(str: String, cb: ((data: String) -> Unit)?) {
        ServiceConfirmDlg.newInst(str).apply {
            showDlg(supportFragmentManager)
        }.onConfirmCb = cb

    }


}