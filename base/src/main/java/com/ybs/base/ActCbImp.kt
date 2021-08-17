package com.ybs.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.ybs.base.manager.ActMgr
import d

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/6/4 14:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/4 14:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class ActCbImp : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        d("actName = ${activity.localClassName}")
        ActMgr.getInst().addActivity(activity)
    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        ActMgr.getInst().removeActivity(activity)
    }
}