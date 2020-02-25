package com.rental.uigastore.service;

import com.rental.uigastore.model.Customer;
import com.rental.uigastore.repository.CustomerRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerTests {

    @Autowired
    private TestEntityManager entityManager;

    @MockBean
    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
        Customer johnDoe = new Customer();
        johnDoe.setId(1L);
        johnDoe.setName("John");
        johnDoe.setBonusPoints(25);

        Mockito.when(customerRepository.findById(johnDoe.getId()))
                .thenReturn(Optional.of(johnDoe));
    }

    @Test
    public void matchCustomerName() {
        Optional<Customer> found = customerRepository.findById(1L);
        found.ifPresent(customer -> assertEquals(customer.getName(), "John"));
    }
}
