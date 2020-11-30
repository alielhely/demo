package com.example.demo.service.impl;

import com.example.demo.mapper.CustomerMapper;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import nl.demo.model.CustomerDetails;
import nl.demo.model.CustomerDetailsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    final static Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;

    }


    @Override
    public Response getCustomerDetails(String customerId) {
        final Customer customer = customerRepository.getCustomer(customerId);
        if (null == customer) {
            LOGGER.error("Customer {} not found ", customerId);
            return Response.status(Response.Status.NOT_FOUND).type(MediaType.APPLICATION_JSON_TYPE).entity("Customer doesn't exist").build();
        }

        CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();
        final CustomerDetails customerDetails = customerMapper.customerToCustomerDetails(customerRepository.getCustomer(customerId));
        customerDetailsResponse.setCustomer(customerDetails);
        Response response = Response.status(Response.Status.OK).entity(customerDetailsResponse).build();
        return response;
    }
}
