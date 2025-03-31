package com.shivraj.loan.service.impl;

import com.shivraj.loan.constant.LoansConstants;
import com.shivraj.loan.dto.LoansDto;
import com.shivraj.loan.entity.Loans;
import com.shivraj.loan.exception.LoanAlreadyExistsException;
import com.shivraj.loan.exception.ResourceNotFoundException;
import com.shivraj.loan.mapper.LoansMapper;
import com.shivraj.loan.repository.LoansRepository;
import com.shivraj.loan.service.lLoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements lLoanService {

    private LoansRepository loansRepository;


    @Override
    public void createLoan(String mobileNumber) {
       Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);

       if(optionalLoans.isPresent()){
           throw new LoanAlreadyExistsException("Loan already registered with give mobile number"+mobileNumber);
       }
       loansRepository.save(createNewLoan(mobileNumber));
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Loan","Mobile Number",mobileNumber));
        return LoansMapper.mapToLoansDto(loans, new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {

        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber())
                .orElseThrow(()-> new ResourceNotFoundException("Loan","Loan Number",loansDto.getLoanNumber()));

        LoansMapper.mapToLoans(loansDto,loans);
        loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Loan","Mobile Number",mobileNumber));
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoans = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoans.setMobileNumber(mobileNumber);
        newLoans.setLoanNumber(Long.toString(randomLoanNumber));
        newLoans.setLoanType(LoansConstants.HOME_LOAN);
        newLoans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoans.setAmountPaid(0);
        newLoans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);

        return newLoans;
    }
}
