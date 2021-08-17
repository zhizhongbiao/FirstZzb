package com.ybs.base.http.response

import com.ybs.base.base.http.response.Status

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

class Resource<out T>(val status: Status, val data: T?, val message: Any?) {
    companion object {
        fun <T> success(data: T?, msg: Any? = null) = Resource(
            Status.SUCCESS,
            data,
            msg
        )

        fun <T> error(msg: Any?, data: T? = null) = Resource(
            Status.ERROR,
            data,
            msg
        )

        fun <T> loading(msg: Any? = null, data: T? = null) = Resource(
            Status.LOADING,
            data,
            msg
        )
    }

    override fun toString(): String {
        return "Resource(status=$status, message=$message, data=$data)"
    }


}