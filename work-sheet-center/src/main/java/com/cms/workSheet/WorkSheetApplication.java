package com.cms.workSheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Creams
 */

@SpringBootApplication
@EnableDiscoveryClient
public class WorkSheetApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkSheetApplication.class, args);
    }
}
