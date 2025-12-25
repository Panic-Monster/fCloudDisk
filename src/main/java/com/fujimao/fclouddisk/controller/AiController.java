package com.fujimao.fclouddisk.controller;

import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.fujimao.fclouddisk.common.BaseResponse;
import com.fujimao.fclouddisk.common.ResultUtils;
import com.fujimao.fclouddisk.service.AiService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Jayson_Y
 * @date: 2025/12/25
 * @project: fCloudDisk
 */
@RestController
@RequestMapping("/ai")
public class AiController {

    @Resource
    private AiService aiService;

    @GetMapping("/request")
    public SseEmitter requestStream() throws NoApiKeyException, InputRequiredException {

        // 1. 调用第三方流式 API（你前面的代码）
        SseEmitter emitter = aiService.callThirdPartyStream();

        return emitter;
    }
}
