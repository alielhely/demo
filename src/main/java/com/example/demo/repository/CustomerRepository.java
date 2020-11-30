package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.springframework.stereotype.Repository;

public interface CustomerRepository {

    Customer getCustomer(String customerId);
}
