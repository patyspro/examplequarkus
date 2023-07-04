package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.acme.model.Customer;
import org.acme.service.CustomerService;
import org.jboss.logging.annotations.Pos;

import java.util.ArrayList;
import java.util.List;

@Path("/api/customer")
public class CustomerController {

@Inject
CustomerService customerService;

@GET
public List <Customer> retrieveCustomers(){

List< Customer> customers =new ArrayList<>();
try{
    customers = customerService.findAllCustomers();
} catch (Exception e){
    e.printStackTrace();
}
return customers;
}

@GET
@Path("/{id}")
public Customer getCustomerById(@PathParam("id") Long id) {

return customerService.findByIdCustomer(id)
        .orElseThrow(() -> new NotFoundException("Customer not found with ID: " + id));
}

@POST
@Transactional //permite a edição no bd
public void addCustomer( Customer customer){

customerService.addCustomer(customer);
}

@DELETE
@Transactional
@Path("/{id}")
public void deleteCustomer(@PathParam("id") Long id) {
    Customer customer = customerService.findByIdCustomer(id)
            .orElseThrow(() -> new NotFoundException("Customer not found with ID: " + id));

    customerService.deleteCustomer(customer);
}

}


