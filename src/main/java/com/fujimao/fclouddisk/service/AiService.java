package com.fujimao.fclouddisk.service;

import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.InputStream;

/**
 * @author: Jayson_Y
 * @date: 2025/12/25
 * @project: fCloudDisk
 */
public interface AiService {

    SseEmitter callThirdPartyStream() throws NoApiKeyException, InputRequiredException;
}
