package com.ybs.base.data;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 服务请求ID定义
 */
public class FeatureRequestConstants {
    public final static String WELCOME = "WELCOME";//欢迎播报
    public final static String SPECIAL_DISH = "SPECIAL_DISH";//打开 特色菜推荐
    public final static String ORDER_DISHES = "SPECIAL_DISH";//扫码点菜
    public final static String ROBOT_INTRODUCTION = "ROBOT_INTRODUCTION";//打开 机器人讲解
    public final static String BRAND_INTRODUCTION = "BRAND_INTRODUCTION";//打开 品牌介绍
    public final static String AMUSEMENT = "AMUSEMENT";//娱乐功能
    public final static String POSITION = "POSITION";//娱乐功能
    public final static String RESTAURANT_SERVICE = "RESTAURANT_SERVICE";//打开 餐厅服务
    public final static String SPECIAL_OFFER = "SPECIAL_OFFER";//打开 优惠活动
    public final static String ADD_SAUCE = "ADD_SAUCE";//打开 加酱料
    public final static String ADD_MEMBER = "ADD_MEMBER";//打开 会员/充值
    public final static String ADD_TABLEWARE = "ADD_TABLEWARE";//打开 加餐具
    public final static String INVOICE = "INVOICE";//打开 开发票
    public final static String ADD_SEAT = "ADD_SEAT";//打开 加座位
    public final static String ADD_APRON = "ADD_APRON";//打开 加围裙
    public final static String PACKAGE = "PACKAGE";//打开 打包盒
    public final static String ADD_TEA = "ADD_TEA";//打开 加茶水
    public final static String ADD_NAPKIN = "ADD_NAPKIN";//打开 加餐巾纸
    public final static String ADD_TOOTHPICK = "ADD_TOOTHPICK";//打开 加牙签
    public final static String ADD_WINE = "ADD_WINE";//打开 加酒水
    public final static String CLEAR_DISH = "CLEAR_DISH";//打开 清盘子
    public final static String ADD_SOUP = "ADD_SOUP";//打开 加汤底
    public final static String CLEAR_FLOOR = "CLEAR_FLOOR";//	打开 扫地
    public final static String CALL_SERVICE = "CALL_SERVICE";//打开 呼叫服务员
    public final static String REMINDER = "REMINDER";//打开 催单
    public final static String UNLIMITED_DRINKING = "UNLIMITED_DRINKING";//打开 无限畅饮

    public final static String DPYX_SERVICE_REMINDER = "DPYX_SERVICE_REMINDER";//催单
    public final static String DPYX_SERVICE_CLEAN_TABLE = "DPYX_SERVICE_CLEAN_TABLE";//清理座位/擦桌子
    public final static String DPYX_SERVICE_ASSOCIATOR_RECHARGE = "DPYX_SERVICE_ASSOCIATOR_RECHARGE";//会员/充值
    public final static String DPYX_SERVICE_DISCOUNT = "DPYX_SERVICE_DISCOUNT";//优惠活动
    public final static String DPYX_SERVICE_ADD_APRON = "DPYX_SERVICE_ADD_APRON";//加围裙
    public final static String DPYX_SERVICE_CHANGE_PLATE = "DPYX_SERVICE_CHANGE_PLATE";//换碟
    public final static String DPYX_SERVICE_PAPER_NAPKIN = "DPYX_SERVICE_PAPER_NAPKIN";//餐巾纸
    public final static String DPYX_SERVICE_TOOTHPICK = "DPYX_SERVICE_TOOTHPICK";//加牙签

    public final static String DPYX_SERVICE_ADD_WATER = "DPYX_SERVICE_ADD_WATER";//加水
    public final static String DPYX_SERVICE_ADD_NINGMEGNWATER = "DPYX_SERVICE_ADD_NINGMEGNWATER";//柠檬水
    public final static String DPYX_SERVICE_ADD_WHITEWATER = "DPYX_SERVICE_ADD_WHITEWATER";//白开水
    public final static String DPYX_SERVICE_ADD_DRINKS = "DPYX_SERVICE_ADD_DRINKS";//其他酒水
    public final static String DPYX_SERVICE_ADD_RICE = "DPYX_SERVICE_ADD_RICE";//加饭
    public final static String DPYX_SERVICE_ADD_SAUCE = "DPYX_SERVICE_ADD_SAUCE";//加酱料
    public final static String DPYX_SERVICE_ADD_CHOPPEDGREENONION = "DPYX_SERVICE_ADD_CHOPPEDGREENONION";//加葱花
    public final static String DPYX_SERVICE_ADD_MASHEDGARLIC = "DPYX_SERVICE_ADD_MASHEDGARLIC";//加蒜泥
    public final static String DPYX_SERVICE_ADD_CAPSICOL = "DPYX_SERVICE_ADD_CAPSICOL";//加辣椒油
    public final static String DPYX_SERVICE_ADD_SALT = "DPYX_SERVICE_ADD_SALT";//加食盐
    public final static String DPYX_SERVICE_ADD_SOYSAUCE = "DPYX_SERVICE_ADD_SOYSAUCE";//加酱油
    public final static String DPYX_SERVICE_ADD_VINEGAR = "DPYX_SERVICE_ADD_VINEGAR";//加醋
    public final static String DPYX_SERVICE_ADD_SOUP = "DPYX_SERVICE_ADD_SOUP";//加汤底
    public final static String DPYX_SERVICE_ADD_TABLEWARE = "DPYX_SERVICE_ADD_TABLEWARE";//加餐具
    public final static String DPYX_SERVICE_ADD_SOUPLADLE = "DPYX_SERVICE_ADD_SOUPLADLE";//加汤勺
    public final static String DPYX_SERVICE_ADD_CHOPSTICKS = "DPYX_SERVICE_ADD_CHOPSTICKS";//加筷子
    public final static String DPYX_SERVICE_ADD_SPOON = "DPYX_SERVICE_ADD_SPOON";//加调羹
    public final static String DPYX_SERVICE_ADD_PACKAGETABLEWARE = "DPYX_SERVICE_ADD_PACKAGETABLEWARE";//加整套餐具
    public final static String DPYX_SERVICE_CHANGE_DISH_WARE = "DPYX_SERVICE_CHANGE_DISH_WARE";//收餐具
    public final static String DPYX_SERVICE_ADD_DISH = "DPYX_SERVICE_ADD_DISH";//加单
    public final static String DPYX_SERVICE_PACKAGE = "DPYX_SERVICE_PACKAGE";//打包
    public final static String DPYX_SERVICE_ADD_SEAT = "DPYX_SERVICE_ADD_SEAT";//加椅子
    public final static String DPYX_SERVICE_ADD_ADDYINGERSEAT = "DPYX_SERVICE_ADD_ADDYINGERSEAT";//加BB凳
    public final static String DPYX_SERVICE_ADD_NORMALSEAT = "DPYX_SERVICE_ADD_NORMALSEAT";//加普通座位
    public final static String DPYX_SERVICE_INVOICE = "DPYX_SERVICE_INVOICE";//开发票
    //        public static String DPYX_SERVICE_BILL = "DPYX_SERVICE_BILL";//结账
    public final static String DPYX_SERVICE_WAITER = "DPYX_SERVICE_WAITER";//呼叫服务员

    public final static String DPYX_SERVICE_SWEEP = "DPYX_SERVICE_SWEEP";//扫地
    public final static String DPYX_SERVICE_BEVERAGE = "DPYX_SERVICE_BEVERAGE";//加酒水
    public final static String DPYX_SERVICE_UNLIMITED_DRINKS = "DPYX_SERVICE_UNLIMITED_DRINKS";//无限畅饮
//    DPYX_SERVICE_SWEEP("DPYX_SERVICE_SWEEP","扫地"),
//    DPYX_SERVICE_BEVERAGE("DPYX_SERVICE_BEVERAGE","加酒水"),
//    DPYX_SERVICE_UNLIMITED_DRINKS("DPYX_SERVICE_UNLIMITED_DRINKS","无限畅饮"),
    public final static String DPYX_SERVICE_DISHES_ARRIVE = "dpyx_service_dishes_arrive";//菜品到达

//    public static int getFeatureServiceIconRes(String serverCode) {
//        int resId = 0;
//        switch (serverCode) {
//            case "DPYX_SERVICE_ADD_WATER"://加水
//            case "DPYX_SERVICE_ADD_NINGMEGNWATER":
//            case "DPYX_SERVICE_ADD_WHITEWATER":
//                resId = R.drawable.icon_addwater;
//                break;
////            case "加米饭":
////                resId = R.drawable.icon_addrice;
////                break;
//            case "DPYX_SERVICE_ADD_TABLEWARE":
//            case "DPYX_SERVICE_ADD_SOUPLADLE":
//            case "DPYX_SERVICE_ADD_CHOPSTICKS":
//            case "DPYX_SERVICE_ADD_SPOON":
//            case "DPYX_SERVICE_ADD_PACKAGETABLEWARE":
//                resId = R.drawable.icon_addtableware;
//                break;
//            case "DPYX_SERVICE_ADD_SAUCE":
//            case "DPYX_SERVICE_ADD_MASHEDGARLIC":
//            case "DPYX_SERVICE_ADD_CHOPPEDGREENONION":
//            case "DPYX_SERVICE_ADD_CAPSICOL":
//            case "DPYX_SERVICE_ADD_SALT":
//            case "DPYX_SERVICE_ADD_SOYSAUCE":
//            case "DPYX_SERVICE_ADD_VINEGAR":
//                resId = R.drawable.icon_addsauce;
//                break;
//            case "DPYX_SERVICE_ADD_SEAT"://加座位
//            case "DPYX_SERVICE_ADD_ADDYINGERSEAT":
//            case "DPYX_SERVICE_ADD_NORMALSEAT":
//                resId = R.drawable.icon_addchair;
//                break;
//            case "DPYX_SERVICE_WAITER":
//                resId = R.drawable.icon_waiter;
//                break;
//            case "DPYX_SERVICE_CHANGE_DISH_WARE":
//            case "DPYX_SERVICE_CHANGE_PLATE"://换碟
//                resId = R.drawable.icon_changeplate;
//                break;
//            case "DPYX_SERVICE_ADD_SOUP":
//                resId = R.drawable.icon_addsoup;
//                break;
////            case "结账":
////                resId= R.drawable.icon_pay;
////                break;
//            case "DPYX_SERVICE_INVOICE":
//                resId = R.drawable.icon_invoice;
//                break;
//            case "DPYX_SERVICE_ADD_DISH":
//                resId = R.drawable.icon_order;
//                break;
//            case "DPYX_SERVICE_PACKAGE":
//                resId = R.drawable.icon_bale;
//                break;
//            case "DPYX_SERVICE_TOOTHPICK":
//                resId = R.drawable.icon_toothpick;
//                break;
//            case "DPYX_SERVICE_ADD_APRON":
//                resId = R.drawable.icon_addapron;
//                break;
//            case "DPYX_SERVICE_PAPER_NAPKIN":
//                resId = R.drawable.icon_napkin;
//                break;
//
//            case "DPYX_SERVICE_BEVERAGE":
//                resId = R.drawable.icon_adddrink;
//                break;
//            case "DPYX_SERVICE_CLEAN_TABLE":
//                resId = R.drawable.icon_cleantable;
//                break;
//            case "DPYX_SERVICE_SWEEP":
//                resId = R.drawable.icon_sweep;
//                break;
//            case "DPYX_SERVICE_REMINDER":
//                resId = R.drawable.icon_urge;
//                break;
//            case "DPYX_SERVICE_MULTI"://多个任务
//                resId = R.drawable.icon_finishall;
//                break;
//            default:
//                resId = R.drawable.icon_addtableware;
//                break;
//        }
//        return resId;
//    }

    public static final Map<String, String> getTaskVoiceMap() {
        Map<String, String> voiceMap = new HashMap<>();
        voiceMap.put("柠檬水", "柠檬水到啦，充沛的维生素C有利于养肝排毒哦");
        voiceMap.put("白开水", "白开水已送达，多多喝水有助于新陈代谢哦");
        voiceMap.put("打包", "您的饭菜已经妥妥打包好啦，记得带走哦");
        voiceMap.put("椅子", "椅子已备好，恭迎您的入座");
        voiceMap.put("婴儿椅", "婴儿椅已搬来，照顾好小天使哦");
        voiceMap.put("酱油", "亲，您要的酱油来啦，快将鲜美的酱汁和美味佳肴一起享用吧");
        voiceMap.put("葱", "亲，葱含有蛋白质，碳水化合物等多种维生素及矿物质，需要随时找我哦");
        voiceMap.put("辣椒油", "亲，恭喜您发现了我家香辣润口的辣椒油，佐餐拌饭，让您用餐更酣畅");
        voiceMap.put("蒜", "亲，适量使用大蒜有助于预防食管癌、胃癌，饮食要均衡，身体更健康");
        voiceMap.put("盐", "亲，每日摄入6g 以下食盐可预防冠心病和高血压，千玺时刻将您的健康放在心上");
        voiceMap.put("醋", "亲，醋是酸溜溜的，但千玺爱你的心是甜蜜蜜的，适量吃醋也能消除疲劳、缓解感冒哦");
        voiceMap.put("汤底", "亲，汤底已为您加好，食材即将吸收饱满汤汁，惊艳您的味蕾");
        voiceMap.put("收餐具", "整洁的桌面，给您用餐好心情，需要其他服务随时叫我哦");
        voiceMap.put("擦桌子", "亲爱的，桌面已为您擦好，闪亮到可以倒映出您美丽的容颜啦");//清理桌位
        voiceMap.put("呼叫服务员", "亲，服务员小姐姐已完成工作，可以帮我到首页左下角进行一下评价吗");
        voiceMap.put("开发票", "亲，发票已经开具成功，可以帮我到首页左下角进行一下评价吗");
        voiceMap.put("碗", "碗已为您呈上，请您享用");
        voiceMap.put("汤勺", "汤勺已就位，注意在就餐过程中不要烫到哦");
        voiceMap.put("筷子", "筷子已经为您备好啦，快开动吧");
        voiceMap.put("盘子", "亲爱的，盘子已经为您收好啦，服务员小姐姐随时待命！");
        voiceMap.put("碗筷", "碗筷已到位，快开动吧");
        voiceMap.put("勺", "勺已到位，祝您用餐愉快");
        voiceMap.put("儿童餐具", "儿童餐具已就位，祝宝宝吃得开心哦");
        voiceMap.put("牙签", "牙签已为您备好，千玺会始终陪伴您度过愉快的用餐时光");
        voiceMap.put("围裙", "围裙已送到，您的衣服那么漂亮，记得保护它不被弄脏哦");
        voiceMap.put("扫地", "亲，地面已经闪亮如新啦，千玺机器人餐厅给您五星级的用餐环境，祝用餐愉快");
        return voiceMap;
    }

    public static final String getMainGuideVoice() {
        String mainGuideVoice;
        List<String> guideVoiceList = new ArrayList<>();
        guideVoiceList.add("试试“小碧同学，介绍一下你自己。”");
        guideVoiceList.add("试试“小碧同学，你都会干啥？”");
        guideVoiceList.add("试试“小碧同学，你们家有什么特色菜？”");
        guideVoiceList.add("试试“小碧同学，我的菜好了没？”");
        guideVoiceList.add("试试“小碧同学，给我加水。”");
        guideVoiceList.add("试试“小碧同学，卫生间在哪？”");
        guideVoiceList.add("试试“小碧同学，给我开发票。”");
        guideVoiceList.add("试试“小碧同学，给我介绍下迎宾机器人。”");
        guideVoiceList.add("试试“小碧同学，给我介绍下碧桂园。”");
        guideVoiceList.add("试试“小碧同学，今天天气怎么样？”");
//        guideVoiceList.add("试试“小碧同学，我想听歌。”");
        mainGuideVoice = guideVoiceList.get(new Random().nextInt(guideVoiceList.size()));
        if (TextUtils.isEmpty(mainGuideVoice)) {
            mainGuideVoice = guideVoiceList.get(0);
        }
        return mainGuideVoice;

    }
}
