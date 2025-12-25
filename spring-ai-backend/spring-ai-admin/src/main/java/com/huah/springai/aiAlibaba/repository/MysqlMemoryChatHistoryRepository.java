package com.huah.springai.aiAlibaba.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huah.springai.aiAlibaba.entity.po.ChatHistoryIdEntity;
import com.huah.springai.aiAlibaba.service.ChatHistoryIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author BlackStar
 * @Date 2025-12-26 01:36:44
 */
@Component
public class MysqlMemoryChatHistoryRepository implements ChatHistoryRepository{

    @Autowired
    private ChatHistoryIdService chatHistoryIdService;

    @Override
    public void save(String type, String userId, String chatId) {
        // 查询数据库是否已有数据
        LambdaQueryWrapper<ChatHistoryIdEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ChatHistoryIdEntity::getUserId, userId);
        lqw.eq(ChatHistoryIdEntity::getType, type);
        lqw.eq(ChatHistoryIdEntity::getChatId, chatId);
        ChatHistoryIdEntity entity = chatHistoryIdService.getOne(lqw);
        if (entity == null) {
            ChatHistoryIdEntity chatHistoryIdEntity = new ChatHistoryIdEntity();
            chatHistoryIdEntity.setUserId(userId);
            chatHistoryIdEntity.setChatId(chatId);
            chatHistoryIdEntity.setType(type);
            chatHistoryIdEntity.setTimestamp(LocalDateTime.now());
            chatHistoryIdService.save(chatHistoryIdEntity);
        }
    }

    @Override
    public List<String> getChatIds(String type, String userId) {
        LambdaQueryWrapper<ChatHistoryIdEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ChatHistoryIdEntity::getUserId, userId);
        lqw.eq(ChatHistoryIdEntity::getType, type);
        List<ChatHistoryIdEntity> list = chatHistoryIdService.list(lqw);
        return list.stream().map(ChatHistoryIdEntity::getChatId).toList();
    }
}
