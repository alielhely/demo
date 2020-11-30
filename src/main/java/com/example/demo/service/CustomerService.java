package com.example.demo.service;

import javax.ws.rs.core.Response;

public interface CustomerService {

    Response getCustomerDetails(final String customerId);
}
