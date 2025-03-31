package com.shivraj.accounts;

import com.shivraj.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Account Microservice Rest Api Documentation",
				description = "VPG Bank Account Microservice Rest Api Documentation",
				version = "v1",
				contact = @Contact(
						name="shivraj",
				        email="shivrajgodale@gmail.com",
						url = "https://www.shivrajgodle.com"
				),
		license = @License(
				name = "Apache License, Version 2.0",
				url = "https://www.shivrajgodle.com"
		)
		),
		externalDocs = @ExternalDocumentation(
				description = "VPG Bank Account Microservice Rest Api Documentation",
				url = "https://www.shivrajgodle.com/swagger-ui.html"
		))
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
