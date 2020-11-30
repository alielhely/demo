package com.example.demo.model;

import com.example.demo.exception.NoSufficientAmountFoundToNewAccountException;
import com.example.demo.exception.NoSufficientBalanceException;
import com.example.demo.store.BankDataStore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    String customerID;
    String name;
    String surName;
    List<Account> accounts;

    public Customer() {
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }


    public String getCustomerID() {
        return customerID;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }


    public synchronized String createAccountWithBalanceFromExitingAccount(BigDecimal balance) {
        final String txId = BankDataStore.generateTxId();
        debitNewAccountInitialBalance(balance, txId);
        String accountNo = createAccountWithBalance(balance, txId);
        return accountNo;
    }

    public String createAccountWithBalance(BigDecimal balance, String txId) {
        String accountNo = this.customerID + "ACC" + BankDataStore.getAccountNoSequence();
        Account account = new Account(accountNo, balance,txId);
        accounts.add(account);
        return accountNo;
    }

    private void debitNewAccountInitialBalance(BigDecimal balance, String txId) {
        if (balance.longValue() > 0) {
            Boolean initialAmountDebited = false;
            for (Account account : this.accounts) {
                try {
                    account.debitAmount(balance, txId);
                    initialAmountDebited = true;
                    break;
                } catch (NoSufficientBalanceException e) {
                    continue;
                }
            }

            if (!initialAmountDebited) {
                throw new NoSufficientAmountFoundToNewAccountException("creating new Account failed as all customer accounts doesn't have sufficient balance for the initial credit");
            }
        }
    }
}
