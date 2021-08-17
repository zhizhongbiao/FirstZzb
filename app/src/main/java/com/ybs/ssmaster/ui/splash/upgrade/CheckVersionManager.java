package com.ybs.ssmaster.ui.splash.upgrade;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.alibaba.android.arouter.utils.Consts;
import com.ybs.base.BaseApp;
import com.ybs.base.data.bean.UpgradeResp;
import com.ybs.base.util.Loger;
import com.ybs.ssmaster.MyApp;
import com.ybs.ssmaster.ui.common.DlgUpdate;

import java.io.File;

/**
 * Create by：ouyangguozhao
 * Create Date：2020/5/7 0007
 * Description：
 */
public class CheckVersionManager {
    private UpgradeResp mUpgradeResp;
    private static ICheckVersionCompleteCallBack mICheckVersionCompleteCallBack;
    private final boolean isSysEnforce = false;

    private static class SingletonHolder {
        private static final CheckVersionManager INSTANCE = new CheckVersionManager();
    }

    public interface ICheckVersionCompleteCallBack {
        void complete(boolean isEnforce);//是否强制升级
    }

    /**
     * CheckVersionManager
     *
     * @return
     */
    public static CheckVersionManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void checkVersion(AppCompatActivity activity, UpgradeResp upgradeResp, ICheckVersionCompleteCallBack callBack) {//是否用户主动检测升级
        mICheckVersionCompleteCallBack = callBack;
        if (null != upgradeResp) {
            mUpgradeResp = upgradeResp;
            if (mUpgradeResp.versionCode > getVersionCode(MyApp.app)) {//存在新版本待升级
                DlgUpdate.Companion.newInst(upgradeResp)
                        .showDlg(activity.getSupportFragmentManager()
                                , DlgUpdate.class.getCanonicalName()).setOnDismissedCb(() -> {
                    if (null != mICheckVersionCompleteCallBack) {
                        mICheckVersionCompleteCallBack.complete(upgradeResp.isEnforce == 1);
                    }
                    return null;
                });
            } else {
                if (null != mICheckVersionCompleteCallBack) {
                    mICheckVersionCompleteCallBack.complete(upgradeResp.isEnforce == 1);
                }
            }
        }
    }


    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (Exception ex) {
            Loger.e(Consts.TAG, "Get package info error.");
        }

        return packageInfo;
    }



    public static void installAPK(Context context, String apkUrl) {
        BaseApp.Companion.setKeepAlive(false);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", new File(apkUrl));
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            Uri uri = Uri.fromFile(new File(apkUrl));
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
