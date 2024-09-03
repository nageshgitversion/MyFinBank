package com.myfinbank.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myfinbank.model.Investment;
import com.myfinbank.service.InvestmentService;

@RestController
@RequestMapping("/api/investments")
@CrossOrigin(origins = "http://localhost:58837")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @PostMapping("/invest")
    public Investment invest(@RequestParam Long accountId, @RequestParam String investmentType, @RequestParam double amount) {
        return investmentService.invest(accountId, investmentType, amount);
    }

    @GetMapping("/account/{accountId}")
    public List<Investment> getInvestmentsByAccountId(@PathVariable Long accountId) {
        return investmentService.getInvestmentsByAccountId(accountId);
    }
}

