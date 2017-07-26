package cloudE.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author vangao1989
 * @Description: 服务发现
 * @date 2017年7月24日
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
