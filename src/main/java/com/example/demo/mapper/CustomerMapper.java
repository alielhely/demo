package com.example.demo.mapper;

import com.example.demo.model.Customer;
import nl.demo.model.CustomerDetails;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDetails customerToCustomerDetails(Customer customer);
}
