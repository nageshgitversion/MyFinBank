package com.myfinbank.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "investments")
public class Investment {
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getInvestmentDate() {
		return investmentDate;
	}

	public void setInvestmentDate(LocalDateTime investmentDate) {
		this.investmentDate = investmentDate;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false)
    private String investmentType; // LOAN, RD, FD

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDateTime investmentDate;

    // Getters and Setters
}
