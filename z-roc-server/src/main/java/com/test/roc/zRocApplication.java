package com.test.roc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.mybatis.spring.annotation.MapperScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SppApplication
 * @Description TODO
 * @Author cosmo
 * @DATE 2023-10-19 10:54
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.test.*"})
@MapperScan("com.test.roc.repository")
@EnableSwagger2
@EnableScheduling
public class zRocApplication {
    public static void main(String[] args) {
        SpringApplication.run(zRocApplication.class, args);
    }
}
