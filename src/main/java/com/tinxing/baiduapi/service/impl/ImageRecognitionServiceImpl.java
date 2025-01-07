package com.tinxing.baiduapi.service.impl;

import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tinxing.baiduapi.dao.ImageRecognitionMapper;
import com.tinxing.baiduapi.model.ImageRecognition;
import com.tinxing.baiduapi.service.ICommonService;
import com.tinxing.baiduapi.service.IImageRecognitionService;
import com.tinxing.baiduapi.utils.ApiResponse;
import com.tinxing.baiduapi.utils.Tools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

/**
 * <p>
 * 图片识别表 服务实现类
 * </p>
 *
 * @author Luckymi
 * @since 2025-01-05
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ImageRecognitionServiceImpl extends ServiceImpl<ImageRecognitionMapper, ImageRecognition> implements IImageRecognitionService {

    private final ICommonService commonService;

    // 动态配置上传目录路径（可在 application.yml 中指定路径）
    @Value("${upload.directory}")
    private String uploadDirectory;

    @Override
    public ApiResponse<String> saveImage(MultipartFile file) {

        // 检查文件是否为空
        if (file.isEmpty()) {
            return ApiResponse.error("文件为空！");
        }

        File uploadDir = new File("src/main/resources", uploadDirectory);

        if (!uploadDir.exists()) {
            if (!uploadDir.mkdirs()) {
                return ApiResponse.error("创建上传目录失败");
            }
        }
        // 生成唯一文件名
        String originalFilename = file.getOriginalFilename();
        //断言 originalFilename != null
        assert originalFilename != null;
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + fileExtension;

        Path path = Paths.get(uploadDir.getAbsolutePath(), newFileName);
        // 目标文件
        try {
            //File destFile = new File(uploadDir, newFileName);
            // 保存文件到目标路径
            file.transferTo(path);
            return ApiResponse.success("/uploads/" + newFileName);
        } catch (IOException e) {
            log.error("保存图片失败: {}", e.getMessage(), e);
            return ApiResponse.error(e.getMessage());
        }
    }

    @Override
    public String anime(String imgUrl) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-process/v1/selfie_anime";
        try {
            if (imgUrl == null || imgUrl.isEmpty()) {
                throw new IllegalAccessException("图片地址不能为空");
            }
            // 本地文件路径
            Path path = Paths.get("src/main/resources" + imgUrl);
            if (!path.toFile().exists()) {
                throw new RuntimeException("图片不存在");
            }
            InputStream stream = path.toFile().toURI().toURL().openStream();
            byte[] imgData = stream.readAllBytes();
            String imgStr = Base64.getEncoder().encodeToString(imgData);
            String imgParam = URLEncoder.encode(imgStr, StandardCharsets.UTF_8);

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String finalUrl = commonService.getAccessToken(url);

            String result = HttpUtil.post(finalUrl, param);
            log.info("漫化图片结果：{}",result);
            return result;
        } catch (Exception e) {
            log.error("漫化图片失败: {}", e.getMessage(), e);
            e.printStackTrace();
            throw new RuntimeException("漫化图片时发生错误");
        }
    }

    @Override
    public String animal(String imgUrl) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/animal";
        try {
            if (imgUrl == null || imgUrl.isEmpty()) {
                throw new IllegalAccessException("图片地址不能为空");
            }
            // 本地文件路径
            Path path = Paths.get("src/main/resources" + imgUrl);
            if (!path.toFile().exists()) {
                throw new RuntimeException("图片不存在");
            }
            InputStream stream = path.toFile().toURI().toURL().openStream();
            byte[] imgData = stream.readAllBytes();
            String imgStr = Base64.getEncoder().encodeToString(imgData);
            String imgParam = URLEncoder.encode(imgStr, StandardCharsets.UTF_8);


            HashMap<String, Object> params = new HashMap<>();
            params.put("image", imgParam);
            params.put("top_num", 3);
            params.put("baike_num", 3);

            String paramStr = Tools.mapToString(params);

            String finalUrl = commonService.getAccessToken(url);

            String result = HttpUtil.post(finalUrl, paramStr);
            log.info("动物识别图片结果：{}",result);
            return result;
        } catch (Exception e) {
            log.error("动物识别图片失败: {}", e.getMessage(), e);
            throw new RuntimeException("动物识别图片时发生错误");
        }
    }

    @Override
    public String plant(String imgUrl) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/plant";
        try {
            if (imgUrl == null || imgUrl.isEmpty()) {
                throw new IllegalAccessException("图片地址不能为空");
            }
            // 本地文件路径
            Path path = Paths.get("src/main/resources" + imgUrl);
            if (!path.toFile().exists()) {
                throw new RuntimeException("图片不存在");
            }
            InputStream stream = path.toFile().toURI().toURL().openStream();
            byte[] imgData = stream.readAllBytes();
            String imgStr = Base64.getEncoder().encodeToString(imgData);
            String imgParam = URLEncoder.encode(imgStr, StandardCharsets.UTF_8);


            HashMap<String, Object> params = new HashMap<>();
            params.put("image", imgParam);
            params.put("baike_num", 3);

            String paramStr = Tools.mapToString(params);

            String finalUrl = commonService.getAccessToken(url);

            String result = HttpUtil.post(finalUrl, paramStr);
            log.info("植物识别图片结果：{}",result);
            return result;
        } catch (Exception e) {
            log.error("植物识别图片失败: {}", e.getMessage(), e);
            throw new RuntimeException("植物识别图片时发生错误");
        }
    }
}
