package com.interview.project.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_order")
public class CustomerOrder extends BaseAuditEntity {

    LocalDateTime orderDate;

    Integer queueNumber;

    String orderStatus;//CREATED, WAITING, COMPLETE

    @ManyToOne(fetch = FetchType.LAZY)
    Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    MenuItem menuItem;
}
