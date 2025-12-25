package com.huah.springai.aiAlibaba.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author BlackStar
 * @Date 2025-12-26 01:27:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("chat_history_ids")
public class ChatHistoryIdEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -102499441099394633L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("userId")
    private String userId;

    @TableField("chatId")
    private String chatId;

    @TableField("type")
    private String type;

    @TableField("timestamp")
    private LocalDateTime timestamp;
}