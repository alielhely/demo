package com.example.demo.provider;

import com.example.demo.exception.CustomerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(1)
public class CustomerNotFoundExceptionMapper implements ExceptionMapper<CustomerNotFoundException> {
    final static Logger LOGGER = LoggerFactory.getLogger(CustomerNotFoundExceptionMapper.class);

    @Override
    public Response toResponse(CustomerNotFoundException e) {
        LOGGER.error("Business Exception: {}", e.getLocalizedMessage());
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
