package mkcloudadmin;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@MapperScan("mkcloudadmin.mapper.mkcloud")
@EnableScheduling
public class MKCloudAdminApplication {
	public static ConfigurableApplicationContext appContect;
	
	public static void main(String[] args) {
		MKCloudAdminApplication.appContect = SpringApplication.run(MKCloudAdminApplication.class, args);
	}

	/**
	 * 文件上传配置
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("10240KB"); //KB,MB
		factory.setMaxRequestSize("102400KB");
		return factory.createMultipartConfig();
	}

}
