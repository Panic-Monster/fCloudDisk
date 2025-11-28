package com.fujimao.fclouddisk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Jayson_Y
 * @date: 2025/11/28
 * @project: fCloudDisk
 */
@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping("health")
    public String health() {
        return "OK";
    }
}
