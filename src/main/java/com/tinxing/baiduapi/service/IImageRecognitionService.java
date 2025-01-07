package com.tinxing.baiduapi.service;

import com.tinxing.baiduapi.model.ImageRecognition;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tinxing.baiduapi.utils.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 图片识别表 服务类
 * </p>
 *
 * @author Luckymi
 * @since 2025-01-05
 */
public interface IImageRecognitionService extends IService<ImageRecognition> {

    ApiResponse<String> saveImage(MultipartFile file);

    String anime(String imgUrl);

    String animal(String imgUrl);

    String plant(String imgUrl);
}
