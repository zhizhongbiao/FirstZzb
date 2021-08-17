package com.ybs.ssmaster.ui.main.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ybs.base.adapter.BaseRvAdpt
import com.ybs.base.adapter.BaseRvHolder
import com.ybs.base.data.bean.QueryRestaurantCommResp
import com.ybs.base.manager.loadImg
import com.ybs.reslib.R
import com.ybs.reslib.databinding.ItemServiceBinding

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
class ServiceAdapt( private val fg: Fragment,
    private val onItemClickCb: ((
        v: View,
        adapter: ServiceAdapt,
        data: QueryRestaurantCommResp,
        pos: Int
    ) -> Unit)? = null
) :
    BaseRvAdpt<QueryRestaurantCommResp, ItemServiceBinding>() {

    override fun getBinding(parent: ViewGroup, viewType: Int) =
        ItemServiceBinding.inflate(LayoutInflater.from(ctx), parent, false)

    override fun onDataBind(
        vh: BaseRvHolder<ItemServiceBinding>,
        data: QueryRestaurantCommResp,
        pos: Int,
        isSelected: Boolean
    ) {
        initClick(vh, pos)

        vh.binding.tvName.text = data.codeValue

        loadImg(data.thumbnailImage,vh.binding.ivService,fg,R.drawable.ic_image_holder)
    }
    

    private fun initClick(
        vh: BaseRvHolder<ItemServiceBinding>,
        pos: Int
    ) {
        vh.binding.root.tag = pos
        vh.binding.root.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val pos = v.tag as Int
        onItemClickCb?.invoke(v, this, dataList[pos], pos)
    }
}