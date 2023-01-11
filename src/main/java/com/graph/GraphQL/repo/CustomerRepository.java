package com.graph.GraphQL.repo;

import com.graph.GraphQL.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, String> {
}
