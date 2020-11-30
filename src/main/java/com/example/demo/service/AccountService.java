package com.example.demo.service;

import nl.demo.model.OpenCurrentAccountRequest;

import javax.ws.rs.core.Response;

public interface AccountService {

    Response addCurrentAccountForExistingCustomer(final OpenCurrentAccountRequest openCurrentAccountRequest);
}
