package com.ybs.ndklib;

public class YbsSoundUtils {
//    private static Context context;

//    deviceTag：设备唯一标识，并不可变
//    productionModelSn：型号标识，有屏音箱取为(XB-YY-1)
//    timestamp:毫秒时间戳
    public static String getDeviceToken(String deviceTag, String timestamp, String productionModelSn) {
        String data = "deviceTag=" + deviceTag + "&productionModelSn=" + productionModelSn + "&timestamp=" + timestamp;
//        String signToken = new JniUtils().signData(data, productionModelSn);
        String signToken = "new JniUtils().signData(data, productionModelSn)";
        return signToken;
    }

    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }


}
