package com.xiaobai.mall.admin.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.xiaobai.mall.exception.MallUploadException;
import com.xiaobai.mall.utils.ResultUtil;
import com.xiaobai.mall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 图片上传controller
 * @create 2020-03-27 16:28
 **/
@RestController
public class ImageController {

    @Autowired
    private FastFileStorageClient storageClient;
    @Value("${FastDFS.addr}")
    private String FastDFSAddr;

    @PostMapping("/image/imageUpload")
    public Result<Object> uploadFile(@RequestParam("file") MultipartFile files) {
        String filename = files.getOriginalFilename();
        // 上传并且生成缩略图
        StorePath storePath = null;
        try {
            storePath = this.storageClient.uploadFile(
                    files.getInputStream(), files.getSize(),filename.substring(files.getOriginalFilename().lastIndexOf(".")+1), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imagePath = storePath.getFullPath();
        if (StringUtils.isEmpty(imagePath)){
            throw new MallUploadException("上传图片异常");
        }
        return new ResultUtil<Object>().setData(FastDFSAddr + imagePath);
    }
}
