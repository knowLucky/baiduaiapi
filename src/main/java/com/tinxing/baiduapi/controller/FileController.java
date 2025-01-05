package com.tinxing.baiduapi.controller;

import com.tinxing.baiduapi.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Luckymi
 */
@RestController
@RequestMapping("/api/files")
public class FileController {

    // 动态配置上传目录路径（可在 application.yml 中指定路径）
    @Value("${upload.directory}")
    private String uploadDirectory;

    @PostMapping("/upload")
    public ApiResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // 检查文件是否为空
        if (file.isEmpty()) {
            return ApiResponse.error("文件为空！");
        }

        // 确保上传目录存在
        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // 创建目录
        }

        // 生成唯一文件名
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        // 目标文件
        File destFile = new File(uploadDir, newFileName);

        try {
            // 保存文件到目标路径
            file.transferTo(destFile);
            return ApiResponse.success("/uploads/" + newFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.error(e.getMessage());
        }
    }
}
