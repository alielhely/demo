package com.example.demo;

import com.example.demo.service.AccountService;
import com.example.demo.service.CustomerService;
import nl.demo.model.OpenCurrentAccountRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Component
@Path("/demo")
public class Resource {
    AccountService accountService;
    CustomerService customerService;

    final static Logger LOGGER = LoggerFactory.getLogger(Resource.class);

    public Resource(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @POST
    @Path("/accounts/current")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAccount(@Valid @NotNull OpenCurrentAccountRequest request) {
        LOGGER.debug("Open current account for exiting customer received : {}", request);
        final Response openCurrentAccountResponse = accountService.addCurrentAccountForExistingCustomer(request);
        return openCurrentAccountResponse;
    }

    @GET
    @Path("/customer/accounts/{customerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerDetails(@Valid @NotNull(message = "customer Id not sent") @PathParam("customerId") String customerId) {
        LOGGER.debug("request received to retrieve details for customer: {}", customerId);
        final Response customerDetailsResponse = customerService.getCustomerDetails(customerId);
        return customerDetailsResponse;
    }

}
