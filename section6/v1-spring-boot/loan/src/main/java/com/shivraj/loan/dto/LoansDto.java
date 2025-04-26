package com.shivraj.loan.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(name = "Loan", description = "Schema to hold loan information")
@Data
public class LoansDto {

    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    @Schema(description="Mobile Number of Customer" , example = "9878654543")
    private String mobileNumber;

    @NotEmpty(message = "Loan Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})" , message = "Loan Number must be 12 digits")
    @Schema(description = "Loan Number of the customer" , example = "543456789876")
    private String loanNumber;

    @NotEmpty(message = "Loan Type can not be a null or empty")
    @Schema(description = "Type of Loan" , example = "Home Loan")
    private String loanType;

    @Positive(message = "Total Loan must be greater than 0")
    @Schema(description = "Total Loan Amount" , example = "100000")
    private int totalLoan;

    @PositiveOrZero(message = "Amount Paid must be greater than or equal to 0")
    @Schema(description = "Total Amount Paid" , example = "50000")
    private int amountPaid;

    @PositiveOrZero(message = "Total Outstanding Amount should be greater than or equal to 0")
    @Schema(description = "Total Outstanding Amount against Loan" , example = "50000")
    private int outstandingAmount;


}
