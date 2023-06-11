package com.daxij1.manageboot.config;

import cn.hutool.http.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class FileServerRunner implements ApplicationRunner {

    @Value("${local-file.port}")
    private int fileServerPort;
    @Value("${local-file.root}")
    private String uploadRoot;

    @Override
    public void run(ApplicationArguments args) {
        /**
         * 创建文件服务
         */
        HttpUtil.createServer(fileServerPort)
                .setRoot(uploadRoot)
                .start();
    }

}
