package com.interview.project.repository;

import com.interview.project.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByUserName(String userName);
}
