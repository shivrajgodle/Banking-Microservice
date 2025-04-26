package com.shivraj.accounts.controller;

import com.shivraj.accounts.constants.AccountConstants;
import com.shivraj.accounts.dto.AccountsContactInfoDto;
import com.shivraj.accounts.dto.CustomerDto;
import com.shivraj.accounts.dto.ErrorResponseDto;
import com.shivraj.accounts.dto.ResponseDto;
import com.shivraj.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(
        name = "CRUD REST APIs FOR VPG ACCOUNT SERVICE",
        description = "CRUD REST APIs in VPG ACCOUNT SERVICE to CREATE ,UPDATE , DELETE and GET ACCOUNT DETAILS"
)
public class AccountController {

    private IAccountService accountService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    AccountController(IAccountService accountService){
        this.accountService = accountService;
    }

    @Operation(summary = "Create a new account Rest api", description = "REST API to Create New Customer and New Account")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Http Status Created"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccounts(@Valid @RequestBody CustomerDto customerDto) {

        accountService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch account details Rest api", description = "REST API to fetch Customer and Account Details on a Mobile Number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                               String mobileNumber){
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(summary = "Update Account Details Rest api", description = "REST API to Update Customer and Account Details based on a Account Number")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Http Status OK"),
                    @ApiResponse(responseCode = "500", description = "Http Status Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
                    @ApiResponse(responseCode = "417", description = "Expectation Failed"),

            }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated = accountService.updateAccount(customerDto);
        if(isUpdated){
          return ResponseEntity
                  .status(HttpStatus.OK)
                  .body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_201));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_417,AccountConstants.MESSAGE_417_UPDATE));
        }
    }


    @Operation(summary = "Delete Account & Customer Details Rest api", description = "REST API to Delete Customer and Account Details based on a Mobile Number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                                String mobileNumber){
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_417,AccountConstants.MESSAGE_417_UPDATE));
        }
    }


    @Operation(summary = "Get build version account service", description = "REST API to fetch build version of account details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/getBuildInfo")
    public ResponseEntity<String> getBuildDetails(){
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }


    @Operation(summary = "Get Java version account service", description = "REST API to fetch Java version of account details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }


    @Operation(summary = "Get Contact Info service", description = "REST API to fetch contact info of developer's in case of any issue details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(accountsContactInfoDto);
    }

}
