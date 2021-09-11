package com.swithExample.driven.models;

import com.swithExample.driven.common.enums.EngineType;
import com.swithExample.driven.models.auditable.AuditableDomain;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "car")
@Setter
@Getter
@NoArgsConstructor
public class Car extends AuditableDomain<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String licensePlate;
    private int seatCount;
    private boolean convertible;
    private int rating;
    private String manufacturer;
    @Enumerated(EnumType.STRING)
    EngineType engineType;
    private double price;
    private boolean isAvailable;
    private boolean isActive;
    private  String username;
}
