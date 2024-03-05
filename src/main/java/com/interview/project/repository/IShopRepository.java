package com.interview.project.repository;

import com.interview.project.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShopRepository extends JpaRepository<Shop, Long> {

}
