package kz.shop.auto_parts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class AutoPartsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoPartsApplication.class, args);
	}

}
