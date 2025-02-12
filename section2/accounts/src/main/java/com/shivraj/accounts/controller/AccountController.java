package com.shivraj.accounts.controller;

import com.shivraj.accounts.constants.AccountConstants;
import com.shivraj.accounts.dto.CustomerDto;
import com.shivraj.accounts.dto.ResponseDto;
import com.shivraj.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountController {

    private IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccounts(@RequestBody CustomerDto customerDto) {

        accountService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));

    }

}
