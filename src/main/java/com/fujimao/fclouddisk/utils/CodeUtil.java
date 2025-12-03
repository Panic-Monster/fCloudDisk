package com.fujimao.fclouddisk.utils;

/**
 * @author: Jayson_Y
 * @date: 2025/12/3
 * @project: fCloudDisk
 */
public class CodeUtil {

    public static String generateCode() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }
}
