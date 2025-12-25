package com.fujimao.fclouddisk.service.impl;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.fujimao.fclouddisk.service.AiService;
import io.reactivex.schedulers.Schedulers;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author: Jayson_Y
 * @date: 2025/12/25
 * @project: fCloudDisk
 */
@Service
public class AiServiceImpl implements AiService {

    @Override
    public SseEmitter callThirdPartyStream() throws NoApiKeyException, InputRequiredException {

        SseEmitter emitter = new SseEmitter(0L); // 不超时

        // 1. 获取 API Key
        String apiKey = "sk-1a48fda9c4ec49d5886e556fcf2df817";

        // 2. 初始化 Generation 实例
        Generation gen = new Generation();

        // 3. 构建请求参数
        GenerationParam param = GenerationParam.builder()
                .apiKey(apiKey)
                .model("qwen-plus")
                .messages(Arrays.asList(
                        Message.builder()
                                .role(Role.USER.getValue())
                                .content("介绍一下自己")
                                .build()
                ))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .incrementalOutput(true) // 开启增量输出，流式返回
                .build();
        // 4. 发起流式调用并处理响应
        // SSE 连接完成或超时处理
        // emitter.onCompletion(() -> System.out.println("客户端断开连接"));
        // emitter.onTimeout(() -> System.out.println("连接超时"));

        // 异步调用流式 API
        gen.streamCall(param)
                .subscribeOn(Schedulers.io())           // IO线程请求API
                .observeOn(Schedulers.computation())    // 处理响应
                .subscribe(
                        // 每个流式片段
                        message -> {
                            try {
                                String content = message.getOutput().getChoices().get(0).getMessage().getContent();
                                if (content != null && !"null".equals(content)) {
                                    emitter.send(SseEmitter.event()
                                            .name("qwen-plus_response")
                                            .data(content));
                                }
                            } catch (IOException e) {
                                emitter.send(SseEmitter.event().name("error").data("模型调用失败"));
                            }
                        },
                        // 异常处理
                        error -> {
                            try {
                                emitter.send(SseEmitter.event()
                                        .name("error")
                                        .data("请求失败: " + error.getMessage()));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            emitter.completeWithError(error);
                        },
                        // 完成处理
                        // emitter::complete
                        () -> {
                            emitter.complete();
                        }
                );
        return emitter;
    }
}
