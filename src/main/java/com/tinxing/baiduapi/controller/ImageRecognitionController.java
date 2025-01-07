package com.tinxing.baiduapi.controller;

import com.tinxing.baiduapi.service.IImageRecognitionService;
import com.tinxing.baiduapi.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图片识别表 前端控制器
 *
 * @author Luckymi
 * @since 2025-01-05
 */
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageRecognitionController {

    private final IImageRecognitionService imageRecognitionService;

    /**
     * 动漫化
     * @param imgUrl 图片路径
     * @return 动漫化图片
     */
    @PostMapping("/anime")
    public ApiResponse<String> anime(String imgUrl) {
        return ApiResponse.success(imageRecognitionService.anime(imgUrl));
    }

    /**
     * 动物识别
     * @param imgUrl 图片路径
     * @return 识别图片
     */
    @PostMapping("/animal")
    public ApiResponse<String> animal(String imgUrl) {
        return ApiResponse.success(imageRecognitionService.animal(imgUrl));
    }

    /**
     * 植物识别
     * @param imgUrl 图片路径
     * @return 识别图片
     */
    @PostMapping("/plant")
    public ApiResponse<String> plant(String imgUrl) {
        return ApiResponse.success(imageRecognitionService.plant(imgUrl));
    }

}
