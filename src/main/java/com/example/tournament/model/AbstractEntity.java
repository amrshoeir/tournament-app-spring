package com.example.tournament.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
abstract public class AbstractEntity {
    public static final String FLD_ID = "id";
    public static final String FLD_NAME = "name";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = FLD_ID)
    private Long Id;
    @Column(name = FLD_NAME, nullable = false, unique = true)
    @NotNull(message = "Name required")
    private String name;

    @Column(name="created")
    @CreationTimestamp
    private Date created;
}
