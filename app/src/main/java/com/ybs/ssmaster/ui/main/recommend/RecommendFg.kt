package com.ybs.ssmaster.ui.main.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ybs.base.data.AppDataManager
import com.ybs.base.data.bean.QuerySpecialDishResp
import com.ybs.reslib.databinding.FgRecommendBinding
import com.ybs.ssmaster.ui.common.AppBaseFg
import com.ybs.ssmaster.ui.main.MainAct
import com.ybs.ssmaster.ui.main.MainVm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

class RecommendFg : AppBaseFg<FgRecommendBinding, MainVm, MainAct>() {

    override fun getVmClz() = MainVm::class.java

    companion object {
        fun newInst() = RecommendFg()
    }

    private val adapt by lazy {
        RecommendAdapt(fg = this) { _, _, data, _ ->

        }
    }


    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FgRecommendBinding.inflate(inflater, container, false)

    override fun initView() {
        binding!!.rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = adapt
        }

        lifecycleScope.launch {
            val filter = getRecommendList()
            adapt.updateAll(filter)
        }
    }

    private suspend fun getRecommendList() = withContext(Dispatchers.Default) {
        mutableListOf<QuerySpecialDishResp>().apply {
            addAll(AppDataManager.inst.allFoodList.filter {
                it.isActive
            })
        }
    }


}