package com.interview.project.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_order")
public class CustomerOrder extends BaseAuditEntity {

    @Builder.Default
    LocalDateTime orderDate = LocalDateTime.now();

    Integer queueNumber;

    String orderStatus;//CREATED, WAITING, COMPLETE

    @ManyToOne(fetch = FetchType.LAZY)
    Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    MenuItem menuItem;
}
