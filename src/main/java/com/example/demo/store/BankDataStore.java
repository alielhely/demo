package com.example.demo.store;

import com.example.demo.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BankDataStore {
    final static Logger logger = LoggerFactory.getLogger(BankDataStore.class);
    final static Map<String, Customer> customers = new HashMap<String, Customer>();
    private static int accountSequenceValue = 1000;
    private static int TxSequenceValue = 0;


    public static void loadData() {
        for (int i = 0; i < 3; i++) {
            final String customerId = "CUSTOMER00" + i + 1;
            Customer customer = new Customer();
            customer.setName(customerId + "_Name");
            customer.setSurName(customerId + "_SurName");
            customer.setCustomerID(customerId);
            customer.createAccountWithBalance(BigDecimal.valueOf((i + 1) * 10), generateTxId());
            customers.put(customerId, customer);
            logger.info("Loading Bank data store with customer Id {} and customer details {}", customerId, customer);
        }
    }

    public static Customer retrieveCustomer(String id) {
        return customers.get(id);
    }

    public static int getAccountNoSequence() {
        accountSequenceValue++;
        return accountSequenceValue;
    }

    public static String generateTxId() {
        TxSequenceValue++;
        return System.currentTimeMillis() + TxSequenceValue + "";
    }
}
