package com.shivraj.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(description = "Name of the Customer", example = "Shivraj")
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5,max = 30,message = "The length of customer name should be between 5 and 30")
    private String name;

    @Schema(description = "Email address of the Customer", example = "shivraj@gmail.com")
    @NotEmpty(message = "Email can not be a null or empty")
    @Email(message = "Email address should be valid value")
    private String email;

    @Schema(description = "Mobile number of the Customer", example = "9975673873")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Account Details of the Customer")
    private AccountsDto accountsDto;
}
