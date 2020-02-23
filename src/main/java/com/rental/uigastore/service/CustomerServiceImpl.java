package com.rental.uigastore.service;

import com.rental.uigastore.dto.CustomerDTO;
import com.rental.uigastore.model.Customer;
import com.rental.uigastore.repository.CustomerRepository;
import com.rental.uigastore.util.DtoToEntityUtil;
import com.rental.uigastore.util.EntityToDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.save(DtoToEntityUtil.convertCustomerDtoToEntity(customerDTO));
        return customer;
    }

    @Override
    public CustomerDTO showCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isEmpty()) {
            throw new NoSuchElementException();
        }

        return EntityToDtoUtil.convertCustomerToDto(customer.get());
    }

    @Override
    public CustomerDTO removeCustomerBonuspoints(Long customerId, Integer bonusDays) {
        Customer customer = customerRepository.getOne(customerId);
        customer.setBonusPoints(customer.getBonusPoints() - (bonusDays * 25));
        customer = customerRepository.save(customer);
        return EntityToDtoUtil.convertCustomerToDto(customer);
    }
}
