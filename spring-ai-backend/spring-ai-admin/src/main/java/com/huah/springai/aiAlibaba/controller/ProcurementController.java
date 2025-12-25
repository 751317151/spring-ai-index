/*
 * Copyright 2024-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huah.springai.aiAlibaba.controller;

import com.huah.springai.aiAlibaba.entity.vo.ApiResponse;
import com.huah.springai.aiAlibaba.service.ProcurementAIService;
import com.huah.springai.aiAlibaba.service.ProcurementCrawlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/procurement")
@RequiredArgsConstructor
public class ProcurementController {
    
    private final ProcurementCrawlerService crawlerService;
    private final ProcurementAIService aiService;
    
    /**
     * 启动爬虫任务
     */
    @PostMapping("/crawl/start")
    public ApiResponse<String> startCrawling(@RequestParam(required = false) String url) {
        try {
            CompletableFuture<String> future;
            if (url != null && !url.trim().isEmpty()) {
                future = crawlerService.startCrawling(url);
            } else {
                future = crawlerService.crawlTianjinProcurement();
            }

            // 异步执行，立即返回
            future.thenAccept(result -> log.info("爬虫任务结果: {}", result));

            return ApiResponse.success("爬虫任务已启动，正在后台执行");
        } catch (Exception e) {
            log.error("启动爬虫失败", e);
            return ApiResponse.error("启动爬虫失败: " + e.getMessage());
        }
    }

    /**
     * 爬取单个页面
     */
    @PostMapping("/crawl/single")
    public ApiResponse<String> crawlSinglePage(@RequestParam String url) {
        try {
            CompletableFuture<String> future = crawlerService.crawlSinglePage(url);
            future.thenAccept(result -> log.info("单页面爬取结果: {}", result));

            return ApiResponse.success("单页面爬取任务已启动");
        } catch (Exception e) {
            log.error("单页面爬取失败", e);
            return ApiResponse.error("单页面爬取失败: " + e.getMessage());
        }
    }

    /**
     * AI分析项目内容
     */
    @PostMapping("/analyze")
    public ApiResponse<String> analyzeProject(@RequestParam String title,
                                       @RequestParam String content,
                                       @RequestParam(required = false) String tableData) {
        try {
            String analysis = aiService.analyzeProcurementData(title, content, tableData);
            return ApiResponse.success(analysis);
        } catch (Exception e) {
            log.error("AI分析失败", e);
            return ApiResponse.error("分析失败: " + e.getMessage());
        }
    }
}
