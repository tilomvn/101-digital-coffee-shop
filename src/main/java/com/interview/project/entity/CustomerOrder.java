package com.interview.project.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    Customer customer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "customerOrder")
    List<CustomerOrderDetail> customerOrderDetail;

    public void setCustomerOrderDetail(List<CustomerOrderDetail> customerOrderDetail) {
        customerOrderDetail.forEach(customerOrderDetail1 -> customerOrderDetail1.setCustomerOrder(this));
        this.customerOrderDetail = customerOrderDetail;
    }
}
