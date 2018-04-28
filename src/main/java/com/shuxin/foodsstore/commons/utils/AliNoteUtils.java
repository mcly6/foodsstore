package com.shuxin.foodsstore.commons.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class AliNoteUtils {

    //官网的URL
    private final static String url = "http://gw.api.taobao.com/router/rest";
    //成为开发者，创建应用后系统自动生成
    private final static String appkey = "LTAIZnBwZNPrHdAz";
    private final static String secret = "EoEXkRsaYTQ1q1jqIyitJe8kd6Uzi4";

    private final static String signName = "智能矿区";

    //
    private final static String warn_message = "SMS_82355007";


    /**
     * 描述:  发送验证码
     * 名称:  reg_send
     * 参数:  @param tel
     * 参数:  @param code
     * 参数:  @return
     *
     * @throws ClientException
     * @throws ServerException
     */
    public static boolean reg_send(String tel, String code, String template) throws ServerException, ClientException {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名
        //替换成你的AK
        final String accessKeyId = "LTAIXyQNDHwO2eVp";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "YOaOvZMezUr1Q1ZodMU35bZOPzHxP7";//你的accessKeySecret，参考本文档步骤2

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();

        // AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();

        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(tel);

        //必填:短信签名-可在短信控制台中找到
        request.setSignName("聊城住房网");

        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(warn_message);

        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{ \"number\":\"" + code + "\"}");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");

        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
            return true;

        } else {
            return false;

        }


    }


    public static void main(String[] args) {
        try {
            reg_send("15863157845", "1102", warn_message);
            System.out.println("发送成功");
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("发送失败");
        }
    }
}
