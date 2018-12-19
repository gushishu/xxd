package com.xxd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 主启动类
 * @author Liang
 * @version 1.0
 */

@EnableAutoConfiguration//自动配置
@EnableEurekaClient//客户端同时也是服务端
@SpringBootApplication//启动
@MapperScan("com.xxd.mappers")//扫描所有的mapper
@ServletComponentScan
public class XXDServerStartApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(XXDServerStartApplication.class, args);
	}

}
