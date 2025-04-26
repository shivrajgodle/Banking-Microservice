package com.shivraj.loan;

import com.shivraj.loan.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loan Microservice Rest API Documentation",
				description = "VPG Bank Loan Microservice Rest API",
				version = "v1",
				contact = @Contact(
						name = "Shivraj",
						email = "shivraj@gmail.com",
						url = "https://www.shivraj.com"
				),
				license =@License(
						name = "Apache License, Version 2.0",
						url = "https://www.shivraj.com"
				)

		),
		externalDocs = @ExternalDocumentation(
				description = "Loan Microservice Rest API Documentation",
				url = "https://www.shivraj.com/swagger-ui.html"
		)
)
@EnableConfigurationProperties(value = {LoansContactInfoDto.class})
public class LoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}

}
