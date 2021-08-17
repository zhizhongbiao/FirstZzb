package com.ybs.base.vm

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ToastUtils
import com.ybs.base.BaseApp
import com.ybs.base.data.AppDataManager
import com.ybs.base.http.response.AppResponse
import com.ybs.base.http.response.Resource
import com.ybs.base.manager.ActMgr
import com.ybs.base.view.tips.TipsFg
import e
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool.base.vm
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/13 20:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/13 20:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
open class BaseVm : ViewModel() {


    val requestCounter = MutableLiveData<Int>()

    private val tipsFg = TipsFg()

    private fun tipsAndExit(msg: String) {
        viewModelScope.launch(Dispatchers.Main) {
            Toast.makeText(
                BaseApp.app,
                msg
                        + "\nSN:${AppDataManager.inst.token}"
                        + "\nMAC:${AppDataManager.inst.deviceMac}",
                Toast.LENGTH_LONG
            ).show()
            val act = ActMgr.getInst().currentActivity() as FragmentActivity
            tipsFg.setMsg(
                msg
                        + "\nSN:${AppDataManager.inst.token}"
                        + "\nMAC:${AppDataManager.inst.deviceMac}"
            ).showDlg(act.supportFragmentManager).onDismissedCb = {
                BaseApp.exitApp()
            }
        }

    }

    private fun incCounter() {
        requestCounter.apply {
            postValue(if (value == null) 1 else value!!.inc())
        }
    }

    private fun decCounter() {
        requestCounter.apply {
            if (value == 0) return@apply
            postValue(value!!.dec())
        }
    }

    protected fun <D> baseRequest(tip: String = "正在请求数据", block: suspend () -> AppResponse<D>) =
        liveData(Dispatchers.IO) {
            incCounter()
            emit(Resource.loading(tip))
            try {
                val data = block()
//                v("data.isOk()  data = $data")
                if (data.isOk()) {
                    emit(Resource.success(data.data, data.message))
                } else {
                    emit(Resource.error(data.message))
                    ToastUtils.showLong("data.msg = ${data.message}   data.code =${data.code}")

                    when (data.code) {
                        "23045" -> tipsAndExit("平板设备未绑定！请联系工作人员绑定设备重启")
                        "28107" -> tipsAndExit("平板设备未绑定桌号！请联系工作人员配置桌号重启")
                    }

                }
            } catch (ex: Exception) {
                e("exp = ${ex.message}")
                emit(Resource.error(ex.message ?: "", data = null))
                ToastUtils.showLong("ex.message  = ${ex.message}   ")
            } finally {
                decCounter()
            }
        }


    protected fun <D> baseRequest2(tip: String = "正在请求数据", block: suspend () -> D) =
        liveData(Dispatchers.IO) {
            incCounter()
//            emit(Resource.loading(tip))
            try {
                val data = block()
//                v("data.isOk()  data = $data")
                emit(Resource.success(data, data))

            } catch (ex: Exception) {
                e("exp = ${ex.message}")
                emit(Resource.error(ex.message ?: "", data = null))
                ToastUtils.showLong("ex.message  = ${ex.message}   ")
            } finally {
                decCounter()
            }
        }


    protected fun <D> basePost(
        dataCb: MutableLiveData<Resource<D>>,
        loadingMsg: String = "正在获取数据",
        block: suspend () -> AppResponse<D>
    ) = viewModelScope.launch(Dispatchers.IO) {
        incCounter()
        dataCb.postValue(Resource.loading(loadingMsg))
        try {
            val response = block()
            if (response.isOk()) {
                dataCb.postValue(Resource.success(response.data))
            } else {
                dataCb.postValue(Resource.error(response.message))
            }
        } catch (e: Exception) {
            e("exp = ${e.message}")
            dataCb.postValue(Resource.error(e.message))
        } finally {
            decCounter()
        }
    }

}