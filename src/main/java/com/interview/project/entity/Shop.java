package com.interview.project.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shop")
public class Shop extends BaseAuditEntity {

    BigDecimal longitude;

    BigDecimal latitude;

    @Column(length = 255)
    String contactDetail;

    Integer maximumSizeOfQueue;

    Integer currentNumberOfQueue;

    LocalTime openTime;

    LocalTime closeTime;

    @ManyToOne(fetch = FetchType.LAZY)
    UserOperator userOperator;
}
