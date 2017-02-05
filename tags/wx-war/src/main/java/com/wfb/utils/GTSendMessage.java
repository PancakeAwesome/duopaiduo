package com.wfb.utils;

import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class GTSendMessage
{
    private static String appId = Constants.GTAPPID;
    private static String appkey = Constants.GTAPPKEY;
    private static String master = Constants.GTMASTERSECRET;
    private static String host = Constants.GTHOST;
    
    public boolean sendMessage(String title, String context) throws Exception{
        
        IGtPush push = new IGtPush(host, appkey, master);
        NotificationTemplate template = notificationTemplateDemo(title, context);
        
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setOffline(true);
        message.setOfflineExpireTime(24 * 1000 * 3600);

        List<String> appIdList = new ArrayList<String>();
//        List<String> phoneTypeList = new ArrayList<String>();
//        List<String> provinceList = new ArrayList<String>();
        
        appIdList.add(appId);
//        phoneTypeList.add("ANDROID");
//        provinceList.add("江苏");
        
        message.setAppIdList(appIdList);
//        message.setPhoneTypeList(phoneTypeList);
//        message.setProvinceList(provinceList);
//       message.setTagList(tagList);
//       message.setPushNetWorkType(1);
//       message.setSpeed(1000);
        IPushResult ret = push.pushMessageToApp(message,"宜通toApp");
        return ret.getResponse().toString().contains("result=ok");
    }
    
    public static TransmissionTemplate TransmissionTemplateDemo(String content)
        throws Exception {
    TransmissionTemplate template = new TransmissionTemplate();
    template.setAppId(appId);
    template.setAppkey(appkey);
    template.setTransmissionType(1);
    template.setTransmissionContent(content);
    
    APNPayload payload = new APNPayload();
    payload.setBadge(1);
    payload.setContentAvailable(1);
    payload.setSound("default");
    payload.setCategory("$由客户端定义");
    payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));
    template.setAPNInfo(payload);
//  template.setPushInfo("actionLocKey", 3, "message", "sound", "payload",
//          "locKey", "locArgs", "launchImage");
    return template;
}
    
    public static LinkTemplate linkTemplateDemo(String title, String content) throws Exception {
        LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appkey);
        template.setTitle(title);
        template.setText(content);
        template.setLogo(Constants.GTLOGO);
        template.setLogoUrl("");
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        template.setUrl("www.manaowan.com");
        return template;
    }
    
    public static NotificationTemplate notificationTemplateDemo(String title, String content) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appkey);
        // 设置通知栏标题与内容
        template.setTitle(title);
        template.setText(content);
        // 配置通知栏图标
        template.setLogo(Constants.GTLOGO);
        // 配置通知栏网络图标
        template.setLogoUrl("www.manaowan.com");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        template.setTransmissionContent(content);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }
}
