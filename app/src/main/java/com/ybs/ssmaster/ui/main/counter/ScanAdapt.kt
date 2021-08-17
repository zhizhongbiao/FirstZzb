package com.ybs.ssmaster.ui.main.counter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ybs.base.adapter.BaseRvAdpt
import com.ybs.base.adapter.BaseRvHolder

import com.ybs.base.data.bean.counter.CountRecordReq
import com.ybs.reslib.databinding.ItemScanBinding



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
class ScanAdapt(private val onItemClickCb: ((
    v: View,
    adapter: ScanAdapt,
    data: CountRecordReq,
    pos: Int
    ) -> Unit)? = null
) :
    BaseRvAdpt<CountRecordReq, ItemScanBinding>() {

    override fun getBinding(parent: ViewGroup, viewType: Int) =
        ItemScanBinding.inflate(LayoutInflater.from(ctx), parent, false)

    override fun onDataBind(
        vh: BaseRvHolder<ItemScanBinding>,
        data: CountRecordReq,
        pos: Int,
        isSelected: Boolean
    ) {
        initClick(vh, pos)

        vh.binding.tvName.text = data.checkId
//        vh.binding.tvCode.text =data.checkAssetsId
        vh.binding.tvLoc.text = ""
        
        vh.binding.tvNum.text = "${pos+1}"


    }
    

    private fun initClick(
        vh: BaseRvHolder<ItemScanBinding>,
        pos: Int
    ) {

    }

    override fun onClick(v: View) {
        val pos = v.tag as Int
        onItemClickCb?.invoke(v, this, dataList[pos], pos)
    }
}