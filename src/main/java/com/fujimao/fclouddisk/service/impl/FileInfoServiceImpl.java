package com.fujimao.fclouddisk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fujimao.fclouddisk.pojo.entity.FileInfo;
import com.fujimao.fclouddisk.service.FileInfoService;
import com.fujimao.fclouddisk.mappers.FileInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【file_info(文件信息表)】的数据库操作Service实现
* @createDate 2025-12-09 08:46:53
*/
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo>
    implements FileInfoService{

}




