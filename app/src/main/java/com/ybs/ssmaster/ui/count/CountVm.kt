package com.ybs.ssmaster.ui.count

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.rscja.deviceapi.RFIDWithUHF
import com.ybs.base.data.bean.counter.CheckDetail
import com.ybs.base.data.bean.counter.CountRecordReq
import com.ybs.base.data.bean.scan.Data
import com.ybs.base.data.bean.scan.ScanBean
import com.ybs.base.manager.TaskManger
import com.ybs.ndklib.YbsSoundUtils
import com.ybs.ssmaster.ui.common.AppVm
import com.ybs.ssmaster.ui.main.recommend.PossessFilterBean
import e

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.ui
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/20 17:05
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/20 17:05
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class CountVm : AppVm() {

    val filter: MutableLiveData<PossessFilterBean> = MutableLiveData(PossessFilterBean())
    var reader: RFIDWithUHF? = null

    private val planList = mutableListOf<CheckDetail>()

    private val scanSet = mutableSetOf<CheckDetail>()
    val scanList = MutableLiveData<MutableList<CheckDetail>>(mutableListOf())

    //    val uploadedListList = MutableLiveData<MutableList<UploadedBean>>(mutableListOf())
    val scanBean = MutableLiveData<ScanBean>()
    val checkedScanBean = MutableLiveData<ScanBean>()

    fun getCountDetail(id: String) = baseRequest2 {
        val countDetail = repos.getCountDetail(id)
        if (countDetail.isOk()) {
            countDetail.data?.checkDetails?.forEach {
                planList.add(it)
            }
        }
        countDetail
    }

    fun postCountedRecord() = baseRequest2 {
        repos.postCountedRecord(req)
    }


    var hasReady = false

    fun initHu() {
        try {
            reader = RFIDWithUHF.getInstance()
        } catch (ex: Exception) {
            ToastUtils.showLong(ex.message)
            return
        }

        TaskManger.inst.execInBg {
            val result = reader?.init()
            hasReady = result == true
            ToastUtils.showLong(if (result == true) "初始化成功" else "初始化失败")
        }
    }


    fun getPossessList(
        id: String,
        code: String,
        storageLocation: String,
        checkStatus: String,
        name: String
    ) = baseRequest2 {
        val possessList = repos.getPossessList(
            id,
            code,
            storageLocation,
            checkStatus,
            name
        )

        if (possessList.isOk()) {
            e("possessList size = ${possessList?.data?.size}")
            possessList.data?.forEach {
                planList.add(it)
//                e("detailBean = $it")
            }
        }

        possessList
    }

    override fun onCleared() {
        reader?.free()
        super.onCleared()
    }

    fun scan() {
        if (!hasReady) {
            ToastUtils.showLong("正在初始化中，请稍后再试")
            return
        }

        var startInventoryTag = reader?.startInventoryTag(0, 0)
        if (startInventoryTag == true) {
            flag.value = true
            t = TagThread(this)
            flag.observeForever {
                t?.tFlag = it
            }
            t?.start()
            ToastUtils.showLong("开始扫描")
        } else {
            ToastUtils.showLong("启动扫描失败，请稍后再试")
            stopScan()
        }
    }

    var compareInfo = "比较："

    fun onScanData(info: String, code: String) {
        e("info =$info")
        val hexStringToString = YbsSoundUtils.hexStringToString(code).trim()
        scanBean.postValue(ScanBean(hexStringToString, System.currentTimeMillis()))
//        planList.find {
//            val b = (it?.code?.trim() ?: "").equals(hexStringToString, true)
//            compareInfo += "\nplanCode=[${it?.code}]  scanHexCode=[${hexStringToString}]  result = $b"
//            b
//            it.hexCode == code
//        }?.apply {
//            scanSet.add(this)
//            scanList.postValue(scanSet.toMutableList())
//            if (!req.checkDetailId.contains(this.id) && checkStatus == "1") {
//                req.checkDetailId.add(this.id ?: -1)
//            }
//        }

        scanInfo.postValue(
            info +
                    "\n START[$hexStringToString]END" +
                    "\n scanCode = $code" +
                    "\n $compareInfo" +
                    "\n planListSize = ${planList.size} " +
                    "\n scanSetSize = ${scanSet.size} " +
                    "\n scanListSize = ${scanList.value?.size} " +
                    "\n req.checkAssetsIdSize = ${req.checkDetailId.size}"
        )
    }


    private var t: TagThread? = null

    fun stopScan() {
        flag.value = (false)
        reader?.stopInventory()
        ToastUtils.showLong("结束扫描")
    }

    fun setCountId(id: String) {
        req.checkId = id
    }

    fun checkIfUploaded(sb: ScanBean, list: MutableList<Data>?) {
        val find = list?.find {
            it.rfid == sb.rfid
        }

        if (find == null) {
            checkedScanBean.postValue(sb)
        }
    }


    val req = CountRecordReq(mutableListOf(), "-1")

    val flag = MutableLiveData(false)
    val scanInfo = MutableLiveData("初始值")


    fun getAllRfRecords() = baseRequest2 { repos.getAllRfRecords() }


    fun deleteRecord(id: String) = baseRequest2 { repos.deleteRecord(id) }

    fun postRfRecordItem(req: ScanBean) = baseRequest2 {
        repos.postRfRecordItem(req)
    }

}