package com.interview.project;

import com.interview.project.entity.Shop;
import com.interview.project.repository.IShopRepository;
import com.interview.project.repository.IUserOperator;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalTime;

@SpringBootApplication
public class DigitalCoffeeShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalCoffeeShopApplication.class, args);
	}

//	@Bean
//	ApplicationRunner runner(IUserOperator iUserOperator, IShopRepository iShopRepository) {
//		return args -> {
//			var userOperator = iUserOperator.findAll().get(0);
//					iShopRepository.save(Shop.builder()
//					.longitude(BigDecimal.TEN)
//					.latitude(BigDecimal.TEN)
//					.closeTime(LocalTime.of(22, 0))
//					.maximumSizeOfQueue(10)
//					.contactDetail("contact details")
//					.currentNumberOfQueue(0)
//					.openTime(LocalTime.of(8, 0))
//					.userOperator(userOperator)
//					.build());
//		};
//	}
}
