package com.rental.uigastore.service;

import com.rental.uigastore.dto.CustomerDTO;
import com.rental.uigastore.exception.ResourceNotFoundException;
import com.rental.uigastore.model.Customer;
import com.rental.uigastore.repository.CustomerRepository;
import com.rental.uigastore.util.DtoToEntityUtil;
import com.rental.uigastore.util.EntityToDtoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        LOGGER.info("createCustomer(): customer creation={}", customerDTO);
        Customer customer = customerRepository.save(DtoToEntityUtil.convertCustomerDtoToEntity(customerDTO));
        LOGGER.info("createCustomer(): customer created={}", customer);
        return customer;
    }

    @Override
    public CustomerDTO showCustomer(Long id) {
        LOGGER.info("showCustomer(): find customer={}", id);
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isEmpty()) {
            LOGGER.error("showCustomer(): customer not found={}", id);
            throw new ResourceNotFoundException("Customer not found");
        }

        LOGGER.info("showCustomer(): found customer={}", customer.get());
        return EntityToDtoUtil.convertCustomerToDto(customer.get());
    }

    @Override
    public CustomerDTO removeCustomerBonuspoints(Long customerId, Integer bonusDays) {
        LOGGER.info("removeCustomerBonuspoints(): customerId={}, bonus points to be removed={}", customerId, bonusDays * 25);
        Customer customer = customerRepository.getOne(customerId);
        customer.setBonusPoints(customer.getBonusPoints() - (bonusDays * 25));
        customer = customerRepository.save(customer);
        LOGGER.info("removeCustomerBonuspoints(): customerId={} bonus points removed", customerId);
        return EntityToDtoUtil.convertCustomerToDto(customer);
    }

    @Override
    public CustomerDTO addCustomerBonuspoints(Long customerId, Integer bonusPoints) {
        LOGGER.info("addCustomerBonuspoints(): customerId={}, bonusPoints to be added={}", customerId, bonusPoints);
        Customer customer = customerRepository.getOne(customerId);
        customer.setBonusPoints(customer.getBonusPoints() + bonusPoints);
        customer = customerRepository.save(customer);
        LOGGER.info("addCustomerBonuspoints(): customerId={}, bonusPoints added={}", customerId, bonusPoints);
        return EntityToDtoUtil.convertCustomerToDto(customer);
    }
}
