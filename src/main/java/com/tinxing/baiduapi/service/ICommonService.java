package com.tinxing.baiduapi.service;

/**
 * 公用 http服务
 * @author Luckymi
 * @date 2025年01月06日 11:42
 */
public interface ICommonService {

    /**
     * 获取accessToken
     * @return String
     */
    String getAccessToken();
    /**
     * 带路径的token
     */
    String getAccessToken(String url);
}
