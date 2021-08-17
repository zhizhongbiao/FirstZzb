package com.ybs.ssmaster.ui.main.service;

import android.text.TextUtils;

import com.ybs.base.data.AppDataManager;
import com.ybs.base.data.bean.QueryVoiceGuideResp;

import java.util.List;
import java.util.Random;

/**
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.ui.main.service
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/28 17:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/28 17:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TtsUtil {

    public static String getTts(String mergeText){

        if (mergeText==null)return null;

        if(mergeText.contains("|")){

            String[] strs = mergeText.split("\\|");
            return strs[new Random().nextInt(strs.length)];

        }else{
            return mergeText;
        }
    }


    public static String getGuideLineTts(String sceneCode){

        if (TextUtils.isEmpty(sceneCode)) return "";

        List<QueryVoiceGuideResp> guidelineList = AppDataManager.Companion.getInst().getGuidelineList();
        for (QueryVoiceGuideResp resp : guidelineList) {
            if (resp.sceneCode.equalsIgnoreCase(sceneCode)) {
                return getTts(resp.sceneText);
            }
        }

        return "";
    }
}
