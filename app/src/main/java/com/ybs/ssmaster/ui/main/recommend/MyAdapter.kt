package com.ybs.ssmaster.ui.main.recommend

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.ui.main.recommend
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/26 11:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/26 11:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class MyAdapter(act: FragmentActivity, private val fgs: MutableList<Fragment>) :
    FragmentStateAdapter(act) {

    override fun createFragment(position: Int) = fgs[position]

    override fun getItemCount() = fgs.size
}