package com.example.demo.repository;

import com.example.demo.store.BankDataStore;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class AccountsRepositoryImpl implements AccountRepository {

    @Override
    public String addCreateAccountToExistingCustomer(String customerId, BigDecimal initialCredit) {
        return BankDataStore.retrieveCustomer(customerId).createAccountWithBalanceFromExitingAccount(initialCredit);
    }
}
