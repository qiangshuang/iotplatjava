package com.ipincloud.iotbj;

import com.ipincloud.iotbj.api.utils.hik.ApiService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.ipincloud.iotbj.**.dao")
public class IotbjApplication extends SpringBootServletInitializer {

	private static boolean hikEnable;

	@Value("${hikEnable}")
	public void setHikEnable(boolean hikEnable) {
		IotbjApplication.hikEnable = hikEnable;
	}


	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(IotbjApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);

		if (hikEnable) {
//			ApiService.getEventFace();
//			ApiService.getEventVehicle();
		}

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(IotbjApplication.class);
	}
}
