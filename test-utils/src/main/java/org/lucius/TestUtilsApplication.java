package org.lucius;

import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestUtilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestUtilsApplication.class, args);
    }

}
