package com.daxij1.manageboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/24 14:55
 * @description：Application
 */
@SpringBootApplication
@EnableTransactionManagement
public class ManageBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageBootApplication.class, args);
    }

}
