package com.interview.project.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_operator")
public class UserOperator extends BaseAuditEntity {

    @Column(length = 20, unique = true, nullable = false)
    String userName;

    @Column(length = 255, nullable = false)
    String password;
}
