package com.tinxing.baiduapi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 图片识别表
 * </p>
 *
 * @author Luckymi
 * @since 2025-01-05
 */
@Getter
@Setter
@TableName("t_image_recognition")
public class ImageRecognition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识符，自增主键
     */
    @TableId("id")
    private Integer id;

    /**
     * 图片存储路径
     */
    @TableField("image_path")
    private String imagePath;

    /**
     * 用于图片识别的模型名称
     */
    @TableField("recognition_model")
    private String recognitionModel;

    /**
     * 图片识别的结果
     */
    @TableField("recognition_result")
    private String recognitionResult;

    /**
     * 图片识别所花费的时间，单位为秒
     */
    @TableField("recognition_duration")
    private BigDecimal recognitionDuration;

    /**
     * 记录创建的时间戳
     */
    @TableField("created_at")
    private LocalDateTime createdAt;

    /**
     * 记录最后更新的时间戳
     */
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    /**
     * 逻辑删除标志，true 表示已删除，false 表示未删除
     */
    @TableField("is_deleted")
    private Boolean isDeleted;
}
