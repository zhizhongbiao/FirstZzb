package com.ybs.ssmaster.ui.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.ui
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/26 19:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/26 19:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class TaskHelper {

    private var runCircleJob: Job? = null

     fun runCircle(time:Long=10*1000,scope: CoroutineScope, b: () -> Unit) {
         cancelJob()
        runCircleJob = scope.launch {
            delay(time)
            b.invoke()
            runCircle(time,scope,b)
        }
    }

     fun cancelJob() {
        runCircleJob?.cancel()
        runCircleJob = null
    }
}