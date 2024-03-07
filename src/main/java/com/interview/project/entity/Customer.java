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

    @Column(length = 12, nullable = false)
    String phoneNumber;

    @Column(length = 30, nullable = false)
    String name;

    @Column(length = 50)
    String address;

    @Column(length = 20, unique = true, nullable = false)
    String userName;

    @Column(length = 255, nullable = false)
    String password;
}
