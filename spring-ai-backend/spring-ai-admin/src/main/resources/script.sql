CREATE TABLE `chat_history_ids` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `userId` varchar(100) NOT NULL,
    `chatId` longtext NOT NULL,
    `type` varchar(100) NOT NULL,
    `timestamp` timestamp NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci