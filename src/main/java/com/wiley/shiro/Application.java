package com.wiley.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;

@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.wiley.shiro.setting.mapper")//配置mybatis包扫描
public class Application{

	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}

	/**
	 * 编码拦截器
	 * 
	 * @return
	 */
	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}
}
