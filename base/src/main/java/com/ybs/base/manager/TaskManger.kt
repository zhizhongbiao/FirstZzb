package com.ybs.base.manager

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base.manager
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/25 21:21
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/25 21:21
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class TaskManger private constructor(){

    companion object{
        val inst=TaskManger()
    }

   private val singleExecutor: ExecutorService by lazy {
        Executors.newSingleThreadExecutor()
    }

    private val executor: ExecutorService by lazy {
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2)
    }

    fun execSerial(block:()->Unit){
        singleExecutor.submit(block)
    }

    fun execInBg(block:()->Unit){
        executor.submit(block)
    }

}