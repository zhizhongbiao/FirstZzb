package com.ybs.ssmaster.ui.main.counter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ybs.base.adapter.BaseRvAdpt
import com.ybs.base.adapter.BaseRvHolder
import com.ybs.base.data.bean.counter.CheckDetail
import com.ybs.reslib.databinding.ItemPossessBinding


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
class CountDetailAdapt(private val onItemClickCb: ((
    v: View,
    adapter: CountDetailAdapt,
    data: CheckDetail,
    pos: Int
    ) -> Unit)? = null
) :
    BaseRvAdpt<CheckDetail, ItemPossessBinding>() {

    override fun getBinding(parent: ViewGroup, viewType: Int) =
        ItemPossessBinding.inflate(LayoutInflater.from(ctx), parent, false)

    override fun onDataBind(
        vh: BaseRvHolder<ItemPossessBinding>,
        data: CheckDetail,
        pos: Int,
        isSelected: Boolean
    ) {
        initClick(vh, pos)

        vh.binding.tvName.text = data.name
        vh.binding.tvCode.text =data.code
        vh.binding.tvLoc.text = data.storageLocation
        vh.binding.tvState.text = if (data.checkStatus=="1")"未盘" else if (data.checkStatus=="2")"已盘" else "已清"
        vh.binding.tvState.isSelected = data.checkStatus!="1"

        vh.binding.tvNum.text = "${pos+1}"


    }
    

    private fun initClick(
        vh: BaseRvHolder<ItemPossessBinding>, pos: Int) {
    }

    override fun onClick(v: View) {
        val pos = v.tag as Int
        onItemClickCb?.invoke(v, this, dataList[pos], pos)
    }
}