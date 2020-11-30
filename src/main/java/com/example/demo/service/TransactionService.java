package com.example.demo.service;

import com.example.demo.exception.NoSufficientAmountFoundToNewAccountException;

import java.math.BigDecimal;

public interface TransactionService {

    void transferInitialAmount(String customerId, String creditAccount, BigDecimal amount, String txId) throws NoSufficientAmountFoundToNewAccountException;
}
