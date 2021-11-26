package com.ybs.ssmaster.ui.scan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ybs.base.adapter.BaseRvAdpt
import com.ybs.base.adapter.BaseRvHolder
import com.ybs.base.data.bean.scan.UploadedBean
import com.ybs.reslib.databinding.ItemNewScanBinding



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
    data: UploadedBean,
    pos: Int
    ) -> Unit)? = null
) :
    BaseRvAdpt<UploadedBean, ItemNewScanBinding>() {

    override fun getBinding(parent: ViewGroup, viewType: Int) =
        ItemNewScanBinding.inflate(LayoutInflater.from(ctx), parent, false)

    override fun onDataBind(
        vh: BaseRvHolder<ItemNewScanBinding>,
        data: UploadedBean,
        pos: Int,
        isSelected: Boolean
    ) {
        initClick(vh, pos)

        vh.binding.tvName.text = data.username
        vh.binding.tvCdTime.text = data.scanTime
        vh.binding.tvTime.text = data.timeConsumingFormat
        vh.binding.tvNo.text = data.id.toString()




    }
    

    private fun initClick(
        vh: BaseRvHolder<ItemNewScanBinding>,
        pos: Int
    ) {
        vh.binding.ibDelete.tag = pos
        vh.binding.ibDelete.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val pos = v.tag as Int
        onItemClickCb?.invoke(v, this, dataList[pos], pos)
    }
}