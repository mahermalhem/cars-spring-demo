package com.swithExample.driven.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(
        name = "driver"
)
@Setter
@Getter
@NoArgsConstructor
public class Driver implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    @NonNull
    private String name;
    private String liscense;
    private String isNew;
    private String tes;

}
