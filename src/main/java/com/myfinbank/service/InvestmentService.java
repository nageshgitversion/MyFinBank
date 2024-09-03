package com.myfinbank.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfinbank.Repository.AccountRepository;
import com.myfinbank.Repository.InvestmentRepository;
import com.myfinbank.model.Account;
import com.myfinbank.model.Investment;

@Service
public class InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Investment invest(Long accountId, String investmentType, double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Investment investment = new Investment();
        investment.setAccount(account);
        investment.setInvestmentType(investmentType);
        investment.setAmount(amount);
        investment.setInvestmentDate(LocalDateTime.now());

        return investmentRepository.save(investment);
    }

    public List<Investment> getInvestmentsByAccountId(Long accountId) {
        return investmentRepository.findByAccountId(accountId);
    }
}

