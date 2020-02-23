package com.rental.uigastore.service;

import com.rental.uigastore.dto.CustomerDTO;
import com.rental.uigastore.model.Customer;

public interface CustomerService {

    Customer createCustomer(CustomerDTO customerDTO);

    CustomerDTO showCustomer(Long id);

    CustomerDTO removeCustomerBonuspoints(Long customerId, Integer bonusDays);
}
