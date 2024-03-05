package com.interview.project.repository;

import com.interview.project.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMenuItemRepository extends JpaRepository<MenuItem, Long> {

}
