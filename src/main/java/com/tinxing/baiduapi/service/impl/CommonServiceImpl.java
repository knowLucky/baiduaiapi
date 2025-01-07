package com.tinxing.baiduapi.service.impl;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.tinxing.baiduapi.properties.BaiduAiProperties;
import com.tinxing.baiduapi.service.ICommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Luckymi
 * @date 2025年01月06日 11:43
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CommonServiceImpl implements ICommonService {

    private final BaiduAiProperties baiduApi;
    private static final Map<String, String> RESPONSE_DATA = new HashMap<>();


    @Override
    public String getAccessToken(){
        if (RESPONSE_DATA != null && !RESPONSE_DATA.isEmpty()) {
            Long endTime = Long.parseLong(RESPONSE_DATA.get("expires_in"));
            if(System.currentTimeMillis() < endTime){
                return RESPONSE_DATA.get("access_token");
            }
            return RESPONSE_DATA.get("access_token");
        }
        String url = baiduApi.getUrl();
        String urlBuilder =
                UrlBuilder.of(url)
                .addQuery("grant_type", baiduApi.getGrantType())
                .addQuery("client_id", baiduApi.getClientId())
                .addQuery("client_secret", baiduApi.getClientSecret())
                .build();
        HttpResponse response = HttpUtil.createPost(urlBuilder)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .execute();
        JSONObject data = new JSONObject(response.body());
        if (data.getStr("error") != null) {
            throw new RuntimeException(data.getStr("error_description"));
        }
        Long startTime = data.getInt("expires_in") / 1000 + System.currentTimeMillis();
        RESPONSE_DATA.put("expires_in", String.valueOf(startTime));
        RESPONSE_DATA.put("access_token", data.getStr("access_token"));
        log.info("\n获取Token出参：{}", response);
        return RESPONSE_DATA.get("access_token");
    }

    @Override
    public String getAccessToken(String url) {
        return UrlBuilder.of(url)
                .addQuery("access_token", getAccessToken())
                .build();
    }
}
