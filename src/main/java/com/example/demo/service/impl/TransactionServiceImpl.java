package com.example.demo.service.impl;

import com.example.demo.exception.NoSufficientAmountFoundToNewAccountException;
import com.example.demo.exception.NoSufficientBalanceException;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    CustomerRepository customerRepository;

    final static Logger LOGGER = LoggerFactory.getLogger(TransactionService.class.getName());

    public TransactionServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public void transferInitialAmount(String customerId, String creditAccount, BigDecimal amount, String txId) {
        Boolean initialAmountDebited = false;
        Customer customer = customerRepository.getCustomer(customerId);
        final List<Account> accounts = customer.getAccounts();
        for (Account account : accounts) {
            if (!creditAccount.equalsIgnoreCase(account.getAccountNo())) {
                try {
                    account.debitAmount(BigDecimal.valueOf(1), txId);
                    initialAmountDebited = true;
                    break;
                } catch (NoSufficientBalanceException e) {
                    continue;
                }
            }
        }
        if (!initialAmountDebited) {
            throw new NoSufficientAmountFoundToNewAccountException("creating new Account as all customer accounts doesn't have sufficient balance for the initial credit");
        }

    }
}
