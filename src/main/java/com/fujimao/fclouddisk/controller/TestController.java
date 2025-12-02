package com.fujimao.fclouddisk.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Jayson_Y
 * @date: 2025/11/28
 * @project: fCloudDisk
 */
@RestController
@RequestMapping("test")
@Tag(name = "测试接口",description = "测试API")
public class TestController {

    @GetMapping("health")
    public String health() {
        return "OK";
    }
}
