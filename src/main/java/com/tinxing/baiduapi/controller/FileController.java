package com.tinxing.baiduapi.controller;

import com.tinxing.baiduapi.service.IImageRecognitionService;
import com.tinxing.baiduapi.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传
 * @author Luckymi
 */
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final IImageRecognitionService imageRecognitionService;

    @PostMapping("/upload")
    public ApiResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return imageRecognitionService.saveImage(file);
    }
}
