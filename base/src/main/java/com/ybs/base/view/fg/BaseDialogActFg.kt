package com.ybs.base.view.fg

import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.ybs.base.view.IActFg


/**
 *
 * @ProjectName:    AW
 * @Package:        com.aylson.aw.view.locate
 * @ClassName:      LocateFg
 * @Description:
 * @Author:         TaxiDriverSantos
 * @CreateDate:     2020/6/12   10:06
 * @UpdateUser:     TaxiDriverSantos
 * @UpdateDate:     2020/6/12   10:06
 * @UpdateRemark:
 * @Version:        1.0
 */
abstract class BaseDialogActFg<B : ViewBinding, A : FragmentActivity> : BaseDialogFg<B>(), IActFg<A> {

    override fun getAct() = requireActivity() as A

}