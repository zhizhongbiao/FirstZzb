package com.ybs.ssmaster.ui.count

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.ybs.reslib.databinding.ActCountDetailBinding
import com.ybs.ssmaster.ui.common.AppBaseAct
import com.ybs.ssmaster.ui.main.counter.CountDetailAdapt
import com.ybs.ssmaster.ui.main.recommend.PossessDetailFilterDlg
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter


const val KEY_ID = "keyId"

class CountDetailAct : AppBaseAct<ActCountDetailBinding, CountDetailVm>() {

    companion object {
        fun startAct(ctx: Context, id: String) {
            ctx.startActivity(Intent(ctx, CountDetailAct::class.java).apply {
                putExtra(KEY_ID, id)
            })
        }
    }

    private val adapt by lazy {
        CountDetailAdapt() { v, a, data, pos ->

        }
    }

    override fun getVmClz() = CountDetailVm::class.java

    override fun getLayoutBinding() = ActCountDetailBinding.inflate(layoutInflater)

    override fun initView() {

        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@CountDetailAct)
            adapter = adapt
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btnClose.setOnClickListener {
            finish()
        }


        binding.btnFilter.setOnClickListener {
            PossessDetailFilterDlg.newInst().showDlg(supportFragmentManager)
        }


        initData()
    }

    private fun initData() {

        intent?.apply {
            val id = getStringExtra(KEY_ID)?: "id is null"

            vm.filter.observe(this@CountDetailAct,{
                vm.getPossessList(
                    id,
                    it.no,
                    it.loc,
                    it.state,
                    it.name
                ).observe(this@CountDetailAct, {
                    if (it?.data?.isOk() == true) {
                        adapt.updateAll(it.data?.data ?: mutableListOf())
//                        ToastUtils.showLong(it.data?.msg ?: "获取数据数据成功！")
                    } else {
                        ToastUtils.showLong(it.data?.msg ?: "获取数据失败！")
                    }
                })


                if (it == null || it.isEmpty()) {
                    binding?.fl?.visibility = View.GONE
                    return@observe
                }

                binding?.fl?.visibility = View.VISIBLE
                tags.apply {
                    clear()
                    add(it.no)
                    add(it.name)
                    add(it.loc)
                    add(when(it.state){
                        "1" ->"未盘"
                        "2" ->"已盘"
                        else ->""
                    })
                }
                a.notifyDataChanged()

            })
        }

    }




    private val tags: ArrayList<String> = arrayListOf()

    private val a by lazy {
        object : TagAdapter<String>(tags) {
            override fun getView(parent: FlowLayout?, position: Int, s: String?) : View {

                val tv = layoutInflater.inflate(
                    com.ybs.ssmaster.R.layout.layout_tv,
                    binding?.fl, false
                ) as TextView
                tv.text = s
                return tv
            }

        }
    }



}