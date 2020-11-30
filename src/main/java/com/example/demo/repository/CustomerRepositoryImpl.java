package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.store.BankDataStore;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer getCustomer(String customerId) {
        return BankDataStore.retrieveCustomer(customerId);
    }
}
