package com.daxij1.manageboot.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.daxij1.manageboot.framework.pojo.ResponseVO;
import com.daxij1.manageboot.pojo.vo.FileUploadResultVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${local-file.address}")
    private String fileAddress;
    @Value("${local-file.root}")
    private String uploadRoot;

    @RequestMapping("/upload")
    @ResponseBody
    public ResponseVO<FileUploadResultVO> upload(MultipartFile file) throws IOException {//实现文件上传
        if (file == null || file.isEmpty()) {
            return ResponseVO.paramFail("上传文件为空");
        }
        String filename = IdUtil.fastSimpleUUID() + "_" + file.getOriginalFilename();
        FileUtil.writeBytes(file.getBytes(), uploadRoot + filename);
        FileUploadResultVO resultVO = new FileUploadResultVO();
        resultVO.setFileName(filename);
        resultVO.setFullUrl(fileAddress + filename);
        return ResponseVO.success(resultVO);
    }

}
