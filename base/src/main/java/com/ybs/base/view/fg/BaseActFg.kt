package com.ybs.base.view.fg

import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.ybs.base.view.IActFg

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/13 17:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/13 17:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
abstract class BaseActFg<B : ViewBinding, A : FragmentActivity> :BaseFg<B>(), IActFg<A> {

    override fun getAct() = requireActivity() as A

}