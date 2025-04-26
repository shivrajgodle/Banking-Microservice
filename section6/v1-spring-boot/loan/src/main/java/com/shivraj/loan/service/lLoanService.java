package com.shivraj.loan.service;

import com.shivraj.loan.dto.LoansDto;

public interface lLoanService {

    void createLoan(String mobileNumber);

    LoansDto fetchLoan(String mobileNumber);

    boolean updateLoan(LoansDto loansDto);

    boolean deleteLoan(String mobileNumber);

}
