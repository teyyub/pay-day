package bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
public class QuotesApplication {
	
//	@Bean
//	public Sampler<?> defaultSampler() {
//		return new AlwaysSampler();
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(QuotesApplication.class, args);
	}
}

