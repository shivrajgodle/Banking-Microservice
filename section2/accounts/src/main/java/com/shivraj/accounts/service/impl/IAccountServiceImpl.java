package com.shivraj.accounts.service.impl;

import com.shivraj.accounts.constants.AccountConstants;
import com.shivraj.accounts.dto.AccountsDto;
import com.shivraj.accounts.dto.CustomerDto;
import com.shivraj.accounts.entity.Accounts;
import com.shivraj.accounts.entity.Customer;
import com.shivraj.accounts.exception.CustomerAlreadyExistsException;
import com.shivraj.accounts.mapper.CustomerMapper;
import com.shivraj.accounts.repository.AccountsRepository;
import com.shivraj.accounts.repository.CustomerRespository;
import com.shivraj.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class IAccountServiceImpl implements IAccountService {


    private AccountsRepository accountsRepository;
    private CustomerRespository customerRespository;



    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer = customerRespository.findByMobileNumber(customerDto.getMobileNumber());

        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number "+customerDto.getMobileNumber());
        }
        customer.setCreatedBy("SBI");
        customer.setCreatedAt(LocalDateTime.now());
        customerRespository.save(customer);
        accountsRepository.save(createNewAccount(customer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        long randomNumber = (long) Math.floor(Math.random() * 9000000000L) + 1000000000L;
        //long randomNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomNumber);

        newAccount.setCreatedBy("SBI");
        newAccount.setCreatedAt(LocalDateTime.now());

        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCustomerId(customer.getCustomerId());
        return newAccount;
    }


}
