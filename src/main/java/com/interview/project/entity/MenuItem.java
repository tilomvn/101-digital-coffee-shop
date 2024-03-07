package com.interview.project.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu_item")
public class MenuItem extends BaseAuditEntity {

    @Column(length = 30, nullable = false)
    String itemName;

    Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    Shop shop;
}
