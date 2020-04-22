package com.cms.workSheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Creams
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class WorkSheetApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkSheetApplication.class, args);
    }
}
