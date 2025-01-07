package com.tinxing.baiduapi.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Luckymi
 */
@Getter
@Setter
@ToString
public class ApiResponse<T> implements Serializable {
    // 状态码
    private int code;
    // 提示信息
    private String message;
    // 返回数据
    private T data;

    // 构造方法
    public ApiResponse() {}

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 静态方法便捷生成成功或失败响应
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(200, "success", null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "success", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, message, data);
    }

    public static <T> ApiResponse<T> error() {
        return new ApiResponse<>(500, "Error", null);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(500, message, null);
    }

    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }

}
