package com.ybs.ssmaster.ui.main.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.ybs.base.adapter.UIUtils
import com.ybs.reslib.databinding.FgQrcodeOrderBinding
import com.ybs.ssmaster.ui.common.AppDlgActFg
import com.ybs.ssmaster.ui.main.MainAct
import com.ybs.ssmaster.ui.main.MainVm
import d
import java.text.SimpleDateFormat


/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.ui.main.process
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/25 19:36
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/25 19:36
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class CountFilterDlg : AppDlgActFg<FgQrcodeOrderBinding, MainAct>() {

    companion object {
        fun newInst() = CountFilterDlg()
    }

    private val vm by lazy {
        retrieveActVm(MainVm::class.java)
    }

//    var dateFormat2: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    var dateFormat2: SimpleDateFormat = SimpleDateFormat("yyyy")

    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FgQrcodeOrderBinding.inflate(inflater, container, false)

    override fun initView(args: Bundle?) {
        binding?.btnReset?.setOnClickListener {

            binding?.tvDate?.text = ""
            binding?.etNo?.setText("")
            binding?.etPerson?.setText("")
            binding?.etYear?.setText("")
        }

//        binding?.tvDate?.setOnClickListener {
//
//            //时间选择器
//            //时间选择器
//            val pvTime = TimePickerBuilder(getAct()) { date, v -> //选中事件回调
//                val format = dateFormat2.format(date)
//                d("$format")
//                binding?.tvDate?.text = format
//
//            }
//                .setLabel("年", "月", "日", "时", "分", "秒")
//                .isDialog(true)
//                .setCancelText("取消")
//                .setSubmitText("确定")
//                .build()
//            pvTime.show()
//        }

        binding?.etYear?.setOnClickListener {

            //时间选择器
            //时间选择器
            val pvTime = TimePickerBuilder(getAct()) { date, v -> //选中事件回调
                val format = dateFormat2.format(date)
                d("$format")
                binding?.etYear?.text = format

            }
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isDialog(true)
                .setCancelText("取消")
                .setSubmitText("确定")
                .build()

            pvTime.show()


        }


        binding?.btnSure?.setOnClickListener {
            var d = binding?.tvDate?.text.toString()
            if (d == "请点击选择时间") d = ""
            vm.filter.postValue(
                FilterBean(
                    d,
                    binding?.etYear?.text.toString(),
                    binding?.etPerson?.text.toString(),
                    binding?.etNo?.text.toString(),
                )
            )
            dismiss()
        }
    }


    override fun getH() = UIUtils.dip2px(300)
    override fun getW() = UIUtils.dip2px(350)


}