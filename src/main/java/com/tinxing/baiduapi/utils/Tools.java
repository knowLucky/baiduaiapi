package com.tinxing.baiduapi.utils;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 公用工具类
 * @author Luckymi
 * @date 2025年01月06日 11:26
 */
public class Tools {

    /**
     * 将 Map 转换为形如 key=value&key2=value2 的字符串
     *
     * @param map 待转换的 Map
     * @return 转换后的字符串
     */
    public static String mapToString(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        return map.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }
}
