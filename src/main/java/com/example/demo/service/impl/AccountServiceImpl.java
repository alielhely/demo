package com.example.demo.service.impl;

import com.example.demo.exception.NoSufficientAmountFoundToNewAccountException;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.service.AccountService;
import nl.demo.model.OpenCurrentAccountRequest;
import nl.demo.model.OpenCurrentAccountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;


@Service
public class AccountServiceImpl implements AccountService {

    final static Logger logger = LoggerFactory.getLogger(AccountService.class.getName());

    CustomerRepository customerRepository;
    AccountRepository accountRepository;

    public AccountServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Response addCurrentAccountForExistingCustomer(OpenCurrentAccountRequest openCurrentAccountRequest) {
        String newAccountNo = null;
        final String customerId = getCustomerId(openCurrentAccountRequest);
        logger.debug("open new account request handling for customer Id: {}", customerId);
        try {
            checkIfCustomerExist(customerId);
            newAccountNo = createNewCurrentAccountForExitingCustomer(openCurrentAccountRequest);
            logger.debug("created new account no {} for customer {}", newAccountNo, customerId);

        } catch (CustomerNotFoundException e) {
            logger.error("customer {} can not be found ", customerId);
            return Response.status(Response.Status.NOT_FOUND).type(MediaType.APPLICATION_JSON_TYPE).entity(e.getLocalizedMessage()).build();
        } catch (NoSufficientAmountFoundToNewAccountException e) {
            logger.error("creating new Account failed as all customer {} accounts doesn't have sufficient balance for the initial credit", customerId);
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON_TYPE).entity(e.getLocalizedMessage()).build();
        }
        return buildSuccessfulResponse(openCurrentAccountRequest, newAccountNo);
    }

    private Response buildSuccessfulResponse(OpenCurrentAccountRequest openCurrentAccountRequest, String newAccountNo) {
        OpenCurrentAccountResponse openCurrentAccountResponse = new OpenCurrentAccountResponse();
        openCurrentAccountResponse.setCustomerID(openCurrentAccountRequest.getCustomerID());
        openCurrentAccountResponse.setMessage("Account " + newAccountNo + " Created successfully");
        openCurrentAccountResponse.setNewCurrentAccountID(newAccountNo);
        Response response = Response.status(Response.Status.OK).entity(openCurrentAccountResponse).build();
        return response;
    }

    private String createNewCurrentAccountForExitingCustomer(OpenCurrentAccountRequest openCurrentAccountRequest) {
        String createdAccountNo = null;
        final String customerID = openCurrentAccountRequest.getCustomerID();
        final BigDecimal initialCredit = openCurrentAccountRequest.getInitialCredit();
        createdAccountNo = accountRepository.addCreateAccountToExistingCustomer(customerID, initialCredit);
        return createdAccountNo;
    }

    private String getCustomerId(OpenCurrentAccountRequest openCurrentAccountRequest) {
        return openCurrentAccountRequest.getCustomerID();
    }

    private void checkIfCustomerExist(String customerId) {
        if (null == customerRepository.getCustomer(customerId)) {
            logger.error("customer {} can not be found ", customerId);
            throw new CustomerNotFoundException("Customer: " + customerId + " doesn't exist");
        }
    }
}
