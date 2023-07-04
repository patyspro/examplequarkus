package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.model.Customer;
import org.acme.repository.CustomerRepository;
import org.acme.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllCustomers() {
        // Mock the behavior of the customerRepository
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new Customer(1L,"jason", "John",19,"jason@email.com"));
        expectedCustomers.add(new Customer(2L,"alice", "John",19,"alice@email.com"));
        when(customerRepository.findAll().list()).thenReturn(expectedCustomers);

        // Call the method under test
        List<Customer> actualCustomers = customerService.findAllCustomers();

        // Verify the results
        assertEquals(expectedCustomers, actualCustomers);
        verify(customerRepository).findAll();
    }

    @Test
    public void testAddCustomer() {
        // Create a new customer
        Customer customer = new Customer(1L,"jason", "John",19,"jason@email.com");

        // Call the method under test
        customerService.addCustomer(customer);

        // Verify that the customerRepository's persist method was called with the customer
        verify(customerRepository).persist(customer);
    }
}
