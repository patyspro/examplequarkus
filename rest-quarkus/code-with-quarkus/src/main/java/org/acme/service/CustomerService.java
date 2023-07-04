package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Customer;
import org.acme.repository.CustomerRepository;


import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        //método que lista todos os clientes
        return customerRepository.findAll().list();

    }

    public void addCustomer(Customer customer) {
        //persist-método que salva objeto no BD
        customerRepository.persist(customer);
    }

    public Optional<Customer> findByIdCustomer(Long id) {
        return customerRepository.findByIdOptional(id);
    }

    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }
}
