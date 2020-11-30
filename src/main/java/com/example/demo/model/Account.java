package com.example.demo.model;

import com.example.demo.exception.NoSufficientBalanceException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    BigDecimal balance;
    String accountNo;

    public List<String> getTransactions() {
        return transactions;
    }

    List<String> transactions;

    public Account(String accountNo, BigDecimal balance, String txId) {
        this.accountNo = accountNo;
        this.transactions = new ArrayList<>();
        this.balance = balance;
        if (balance.longValue() > 0)
            transactions.add(txId);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public synchronized void debitAmount(BigDecimal amount, String txId) throws NoSufficientBalanceException {
        if (this.balance.min(amount).compareTo(amount) != -1) {
            this.balance = this.balance.subtract(amount);
            transactions.add(txId);
        } else
            throw new NoSufficientBalanceException();
    }

    public void creditAmount(BigDecimal amount) {

        this.balance = this.balance.add(amount);

    }

}
