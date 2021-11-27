package com.ybs.ssmaster.ui.count;

import com.ybs.base.util.Loger;

/**
 * @ProjectName: FirstZzb
 * @Package: com.ybs.ssmaster.ui.count
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/8/7 19:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/7 19:40
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TagThread extends Thread {

    private CountVm vm;

    public volatile boolean tFlag=false;

    TagThread(CountVm vm) {
        this.vm = vm;
        tFlag=vm.getFlag().getValue();
    }

    public void run() {
        String strTid;
        String strResult;
        String[] res = null;
        while (tFlag) {

            res = vm.getReader().readTagFromBuffer();
            if (res != null) {
                strTid = res[0];
                if (strTid.length() != 0 && !strTid.equals("0000000" +
                        "000000000") && !strTid.equals("000000000000000000000000")) {
                    strResult = "TID:" + strTid + "\n";
                } else {
                    strResult = "";
                }
                Loger.i("data", "EPC:" + res[1] + "|" + strResult);
                final String code = vm.getReader().convertUiiToEPC(res[1]);

                String info = strResult + "EPC:" + code+ "@" + res[2];

               vm.onScanData(info,code);
            }
        }

        vm=null;
    }
}
