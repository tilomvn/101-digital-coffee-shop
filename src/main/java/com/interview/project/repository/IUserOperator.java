package com.interview.project.repository;

import com.interview.project.entity.UserOperator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserOperator extends JpaRepository<UserOperator, Long> {

}
