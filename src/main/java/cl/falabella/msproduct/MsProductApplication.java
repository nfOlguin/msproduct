package cl.falabella.msproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = "cl.falabella")
public class MsProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProductApplication.class, args);
	}

}
