package com.ybs.ssmaster.ui.main.recommend

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ybs.base.adapter.BaseRvAdpt
import com.ybs.base.adapter.BaseRvHolder
import com.ybs.base.data.bean.QuerySpecialDishResp
import com.ybs.reslib.R
import com.ybs.reslib.databinding.ItemRecommendBinding

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
class RecommendAdapt(
    private val fg: Fragment,
    private val onItemClickCb: ( (
        v: View,
        adapter: RecommendAdapt,
        data: QuerySpecialDishResp,
        pos: Int
    ) -> Unit)? = null) :
    BaseRvAdpt<QuerySpecialDishResp, ItemRecommendBinding>() {

    override fun getBinding(parent: ViewGroup, viewType: Int) =
        ItemRecommendBinding.inflate(LayoutInflater.from(ctx), parent, false)

    override fun onDataBind(
        vh: BaseRvHolder<ItemRecommendBinding>,
        data: QuerySpecialDishResp,
        pos: Int,
        isSelected: Boolean
    ) {
        initClick(vh, pos)

        vh.binding.tvName.text = data.specialDishTitle
        val temp = "￥：${if (TextUtils.isEmpty(data.specialDishPrice)) "时价" else data.specialDishPrice}"
        vh.binding.tvPrice.text = temp

        Glide
            .with(fg)
            .load(data.specialDishImgUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_image_holder)
            .into(vh.binding.ivFood)

    }


    private fun initClick(
        vh: BaseRvHolder<ItemRecommendBinding>,
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