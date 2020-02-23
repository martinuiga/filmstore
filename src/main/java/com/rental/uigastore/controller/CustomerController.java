package com.rental.uigastore.controller;

import com.rental.uigastore.dto.CustomerDTO;
import com.rental.uigastore.model.Customer;
import com.rental.uigastore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add_customer")
    public ResponseEntity<Customer> addNewCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/show_customer/{id}")
    public ResponseEntity<CustomerDTO> showCustomer(@PathVariable Long id) {
        CustomerDTO customerDTO = customerService.showCustomer(id);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }
}
