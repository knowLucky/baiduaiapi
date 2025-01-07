package com.tinxing.baiduapi.controller;

import com.tinxing.baiduapi.service.IImageRecognitionService;
import com.tinxing.baiduapi.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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
