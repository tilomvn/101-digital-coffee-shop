package com.interview.project.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_order_detail")
public class CustomerOrderDetail extends BaseAuditEntity {

    Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    CustomerOrder customerOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    MenuItem menuItem;
}
