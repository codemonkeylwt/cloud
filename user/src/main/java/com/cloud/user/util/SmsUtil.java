package com.cloud.user.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Random;

/**
 * 阿里大鱼短信服务
 *
 * @author lwt
 * @date 2018/7/23 15:20
 */
public class SmsUtil {
    private static Properties properties;
    static {
        properties = ExcutePro.getPro("SmsIni.properties");
    }
    private final static Logger logger = LoggerFactory.getLogger(SmsUtil.class);
    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    private static final String PRODUCT = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";

    /**
     * TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
     */
    private static final String ACCESSKEYID = properties.getProperty("ACCESSKEYID");
    private static final String ACCESSKEYSECRET = properties.getProperty("ACCESSKEYSECRET");
    private static final int CODELENGTH = 4;
    private static final String DIC = "qazwsxedcrfvtgbyhnujmikolp123456789QAZWSXEDCRFVTGBYHNUJMIKOLP";
    public static JsonResult sendSms(String telphone) throws ClientException {
        JsonResult jsonResult = new JsonResult();

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESSKEYID, ACCESSKEYSECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(telphone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("刘文韬");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_136710099");
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < CODELENGTH; i++) {
            stringBuilder.append(DIC.charAt(random.nextInt(DIC.length())));
        }
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\""+stringBuilder.toString()+"\"}");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        try {
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse != null) {
                return jsonResult.setResult(stringBuilder.toString());
            }
        } catch (ClientException e) {
            e.printStackTrace();
            logger.error("短信发送失败(alibaba)[{}]", ExceptionUtil.getExceptionInfo(e));
            return jsonResult.setSuccess(false);
        }
        return jsonResult.setSuccess(false);
    }
}
