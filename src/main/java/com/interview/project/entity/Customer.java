package com.interview.project.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer extends BaseAuditEntity {

    @Column(length = 12)
    String phoneNumber;

    @Column(length = 30)
    String customerName;

    @Column(length = 50)
    String address;

    @Column(length = 20)
    String userName;

    @Column(length = 255)
    String password;
}
