package com.ybs.ssmaster.ui.main.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ybs.base.adapter.BaseRvAdpt
import com.ybs.base.adapter.BaseRvHolder
import com.ybs.base.data.bean.QueryRestaurantCommResp
import com.ybs.reslib.databinding.ItemCountPlanBinding


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
class OptionAdapt(private val fg: Fragment,
                  private val onItemClickCb: ((
                      v: View,
                      adapter: OptionAdapt,
                      data: QueryRestaurantCommResp,
                      pos: Int
    ) -> Unit)? = null
) :
    BaseRvAdpt<QueryRestaurantCommResp, ItemCountPlanBinding>() {

    override fun getBinding(parent: ViewGroup, viewType: Int) =
        ItemCountPlanBinding.inflate(LayoutInflater.from(ctx), parent, false)

    override fun onDataBind(
        vh: BaseRvHolder<ItemCountPlanBinding>,
        data: QueryRestaurantCommResp,
        pos: Int,
        isSelected: Boolean
    ) {



    }
    



    override fun onClick(v: View) {

    }
}