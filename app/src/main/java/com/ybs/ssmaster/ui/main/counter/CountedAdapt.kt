package com.ybs.ssmaster.ui.main.counter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ybs.base.adapter.BaseRvAdpt
import com.ybs.base.adapter.BaseRvHolder
import com.ybs.base.data.bean.counter.Row

import com.ybs.reslib.databinding.ItemCountedBinding

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool.box.wifi
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/16 9:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/16 9:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class CountedAdapt(private val onItemClickCb: ((
    v: View,
    adapter: CountedAdapt,
    data: Row,
    pos: Int
    ) -> Unit)? = null
) :
    BaseRvAdpt<Row, ItemCountedBinding>() {

    override fun getBinding(parent: ViewGroup, viewType: Int) =
        ItemCountedBinding.inflate(LayoutInflater.from(ctx), parent, false)

    override fun onDataBind(
        vh: BaseRvHolder<ItemCountedBinding>,
        data: Row,
        pos: Int,
        isSelected: Boolean
    ) {
        initClick(vh, pos)

        vh.binding.tvName.text = data.checkNo
        vh.binding.tvDate.text = "盘点日期："+data.checkDate
        vh.binding.tvYear.text = "盘点年份："+data.checkYear
        vh.binding.tvPerson.text = "盘点人："+data.checkPerson
        vh.binding.tvCounted.text = "已盘："+(data.checkNum?:"0")
        vh.binding.tvNotCount.text =  "未盘："+(data.noCheckNum?:"0")
        vh.binding.tvClearedCount.text =  "已清："+(data.cleanNum?:"0")
        vh.binding.tvDoneTime.text =  "完成时间："+(data.updateTime?:"暂无记录")

    }
    

    private fun initClick(
        vh: BaseRvHolder<ItemCountedBinding>,
        pos: Int
    ) {
        vh.binding.btnCheck.tag = pos
        vh.binding.btnCheck.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        val pos = v.tag as Int
        onItemClickCb?.invoke(v, this, dataList[pos], pos)
    }
}