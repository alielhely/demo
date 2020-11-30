package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepository {
    String addCreateAccountToExistingCustomer(String customerId, BigDecimal initialCredit);
}
