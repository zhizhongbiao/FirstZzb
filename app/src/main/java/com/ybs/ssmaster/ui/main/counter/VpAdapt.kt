package com.ybs.ssmaster.ui.main.counter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *
 * @ProjectName: FirstZzb
 * @Package: com.ybs.ssmaster.ui.main.counter
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/8/7 11:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/7 11:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class VpAdapt(private val fgs: MutableList<Fragment>,
              private val fragmentManager: FragmentManager, val lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount()=fgs.size

    override fun createFragment(position: Int)=fgs[position]
}