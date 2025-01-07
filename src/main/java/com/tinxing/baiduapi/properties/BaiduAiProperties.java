package com.tinxing.baiduapi.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Luckymi
 * @date 2025年01月06日 11:35
 */
@Data
@Component
@ConfigurationProperties(prefix = "baiduapi")
public class BaiduAiProperties {
    /**
     * 应用的Secret Key
     */
    private String clientSecret;
    /**
     * 应用的API Key
     */
    private String clientId;
    /**
     * 固定为client_credentials
     */
    private String grantType = "client_credentials";
    /**
     * 请求地址,获取token
     */
    private String url = "https://aip.baidubce.com/oauth/2.0/token";
}
