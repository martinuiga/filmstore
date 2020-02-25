package com.rental.uigastore.service;

import static org.junit.Assert.assertEquals;

import com.rental.uigastore.model.Customer;
import com.rental.uigastore.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void addCustomerAndMatchNames() {
        // given
        Customer johnDoe = new Customer();
        johnDoe.setId(1L);
        johnDoe.setName("John");
        johnDoe.setBonusPoints(25);
        entityManager.persist(johnDoe);
        entityManager.flush();

        // when
        Optional<Customer> found = customerRepository.findById(1L);

        // then
        found.ifPresent(customer -> assertEquals(customer.getName(), johnDoe.getName()));
    }
}
