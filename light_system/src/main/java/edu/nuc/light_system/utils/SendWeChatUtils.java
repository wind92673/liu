package edu.nuc.light_system.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.nuc.light_system.entity.vo.IacsUrlDataVo;
import edu.nuc.light_system.entity.vo.IacsWeChatDataVo;
import edu.nuc.light_system.exception.BusinessException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SendWeChatUtils
 * @Description //企业微信消息发送
 * @Author 19265
 * @Date 2026/4/15 10:13
 * @Version 1.0.0
 */
@Component
public class SendWeChatUtils {

    private static final Logger logger = LoggerFactory.getLogger(SendWeChatUtils.class);

    private CloseableHttpClient httpClient;
    private static HttpPost httpPost;//用于提交登陆数据
    private  static HttpGet httpGet;//用于获得登录后的页面
    public static final String CONTENT_TYPE = "Content-Type";
    static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Value("${wechat.enterId}")
    private String enterId;

    @Value("${wechat.secret}")
    private String secret;

    private static String staticEnterId;
    private static String staticSecret;

    @PostConstruct
    public void init() {
        staticEnterId = this.enterId;
        staticSecret = this.secret;
    }

    /**
     * 微信授权请求，GET类型，获取授权响应，用于其他方法截取token
     *
     * @param Get_Token_Url
     * @return String 授权响应内容
     * @throws IOException
     */
    public String toAuth(String Get_Token_Url) throws IOException {

        httpClient = HttpClients.createDefault();
        httpGet = new HttpGet(Get_Token_Url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String resp;
        try {
            HttpEntity entity = response.getEntity();
            resp = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        logger.info(" resp:{}", resp);
        return resp;
    }

    /**
     * 获取toAuth(String Get_Token_Url)返回结果中键值对中access_token键的值
     *
     * @param corpid 应用组织编号   corpsecret 应用秘钥
     */
    public static String getToken(String corpid, String corpsecret) throws IOException {
        SendWeChatUtils sw = new SendWeChatUtils();
        IacsUrlDataVo uData = new IacsUrlDataVo();
        uData.setGet_Token_Url(corpid, corpsecret);
        String resp = sw.toAuth(uData.getGet_Token_Url());

        JSONObject jsonObject = JSON.parseObject(resp);
        return jsonObject.getString("access_token");
    }

    public static String getToken() throws IOException {
        return getToken(staticEnterId, staticSecret);
    }



    /**
     * @param touser         发送消息接收者    ，msgtype消息类型（文本/图片等），
     * @param application_id 应用编号。
     * @return String
     * @Title:创建微信发送请求post数据
     */
    public static String createpostdata(String touser, String msgtype,
                                        int application_id, String contentKey, String contentValue) {
        IacsWeChatDataVo wcd = new IacsWeChatDataVo();
        wcd.setTouser(touser);
        wcd.setAgentid(application_id);
        wcd.setMsgtype(msgtype);
        Map<Object, Object> content = new HashMap<Object, Object>();
        content.put(contentKey, contentValue + "\n--------\n" + df.format(new Date()));
        wcd.setText(content);
        return JSON.toJSONString(wcd);
    }

    /**
     * @param charset 消息编码    ，contentType 消息体内容类型，
     * @param url     微信消息发送请求地址，data为post数据，token鉴权token
     * @return String
     * @Title 创建微信发送请求post实体
     */
    public static String post(String charset, String contentType, String url,
                              String data, String token) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        httpPost = new HttpPost(url + token);
        httpPost.setHeader(CONTENT_TYPE, contentType);
        httpPost.setEntity(new StringEntity(data, charset));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        String resp;
        try {
            HttpEntity entity = response.getEntity();
            resp = EntityUtils.toString(entity, charset);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        logger.info("call [{}], param:{}, resp:{}", url, data, resp);
        return resp;
    }


    public static void sendMsg(String strContent){
        try {
            String token = getToken();
            String postdata = createpostdata("@all", "text", 1000011, "content",strContent);
            String resp = post("utf-8", "application/json", (new IacsUrlDataVo()).getSendMessage_Url(), postdata, token);
            logger.info("获取到的token======>" + token);
            logger.info("请求数据======>" + postdata);
            logger.info("发送微信的响应数据======>" + resp);
        } catch (Exception e) {
            throw new BusinessException("发送企业微信消息失败");
        }
    }
}
