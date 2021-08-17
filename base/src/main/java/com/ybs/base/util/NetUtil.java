package com.ybs.base.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base.util
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/6/1 11:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/1 11:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class NetUtil {

    public static final int TYPE_WIFI = 1;
    public static final int TYPE_MOBILE = 2;
    public static final int TYPE_NOT_CONNECTED = 0;


    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

}


