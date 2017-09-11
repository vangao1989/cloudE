package cloudE.zuul.server;

import cloudE.zuul.server.filter.AccessFilter;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


/**
 * @author vangao1989
 * @Description: 网关
 * @date 2017年7月26日
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableSpringBootMetricsCollector
public class GatewayServerApplication {

	public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

}
