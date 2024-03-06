package com.interview.project.repository;

import com.interview.project.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

}
