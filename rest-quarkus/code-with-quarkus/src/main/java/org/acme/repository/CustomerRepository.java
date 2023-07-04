package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Customer;

@ApplicationScoped //essa classe vai utilizar as dependências do Quarkus
//Essa classe vai ser referência para o nosso BD
public class CustomerRepository implements PanacheRepository<Customer> {
}
