package andreialionte.realestatebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
public class RealestatebackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealestatebackendApplication.class, args);
	}

}
