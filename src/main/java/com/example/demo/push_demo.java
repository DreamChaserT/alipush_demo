package com.example.demo;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.push.model.v20160801.PushMessageToAndroidRequest;
import com.aliyuncs.push.model.v20160801.PushMessageToAndroidResponse;
import com.aliyuncs.push.model.v20160801.PushRequest;
import com.aliyuncs.push.model.v20160801.PushResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cmglz on 2017/7/8.
 */
public class push_demo {
    public static void  push() throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIgk3fPzok5k2P", "u5nUn1XqTQzxv9PLe8SveAmfo4Q3VK");
        DefaultAcsClient client = new DefaultAcsClient(profile);


        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        final String date = dateFormat.format(new Date());
        PushRequest pushRequest = new PushRequest();
        //推送内容需要保护，请使用HTTPS协议
        pushRequest.setProtocol(ProtocolType.HTTPS);
        //推送内容较长，请使用POST请求
        pushRequest.setMethod(MethodType.POST);

        // 推送目标
        pushRequest.setAppKey((long) 24537595);
        pushRequest.setTarget("ALL"); //推送目标: device:推送给设备; account:推送给指定帐号,tag:推送给自定义标签; all: 推送给全部
        pushRequest.setTargetValue("ALL"); //根据Target来设定，如Target=device, 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
        pushRequest.setDeviceType("ANDROID"); // 设备类型deviceType 取值范围为:0~3. iOS设备: 0; Android设备: 1; 全部: 3, 这是默认值.


        // 推送配置
        pushRequest.setPushType("NOTICE");
        pushRequest.setTitle(date); // 消息的标题
        pushRequest.setBody("PushRequest body"); // 消息的内容
        // 推送配置: Android
        //设置该参数后启动小米托管弹窗功能，此处指定通知点击后跳转的Activity（托管弹窗的前提条件：1. 继承小米辅助通道；2. storeOffline设为true
        //pushRequest.setXiaomiActivity("_Your_XiaoMi_Activity_");
        pushRequest.setAndroidOpenType("URL"); // 点击通知后动作,1:打开应用 2: 打开应用Activity 3:打开 url 4 : 无跳转逻辑
        pushRequest.setAndroidOpenUrl("http://www.baidu.com"); // Android收到推送后打开对应的url,仅仅当androidOpenType=3有效
        pushRequest.setAndroidExtParameters("{\"k1\":\"android\",\"k2\":\"v2\"}"); // 设定android类型设备通知的扩展属性


        // 推送控制
        //final Date pushDate = new Date(System.currentTimeMillis() + 30 * 1000); // 30秒之间的时间点, 也可以设置成你指定固定时间
        //final String pushTime = ParameterHelper.getISO8601Time(pushDate);
        //pushRequest.setPushTime(pushTime); // 延后推送。可选，如果不设置表示立即推送
        //pushRequest.setStoreOffline(false); // 离线消息是否保存,若保存, 在推送时候，用户即使不在线，下一次上线则会收到
        //final String expireTime = ParameterHelper.getISO8601Time(new Date(System.currentTimeMillis() + 12 * 3600 * 1000)); // 12小时后消息失效, 不会再发送
        //pushRequest.setExpireTime(expireTime);

        PushResponse pushResponse = client.getAcsResponse(pushRequest);
        System.out.printf("RequestId: %s\n",
                pushResponse.getRequestId());
    }
}
