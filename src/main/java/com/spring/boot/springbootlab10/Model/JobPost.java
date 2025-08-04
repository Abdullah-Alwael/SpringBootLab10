package com.spring.boot.springbootlab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title can not be empty")
    @Size(min = 5, message = "title must not be less than 5 characters long")
    @Column(columnDefinition = "varchar(30) not null")
    private String title;

    @NotEmpty(message = "description can not be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String description;

    @NotEmpty(message = "location can not be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String location;

    @NotNull(message = "salary can not be empty")
    @PositiveOrZero(message = "salary must be a positive number or zero")
    @Column(columnDefinition = "double not null chack(salary>=0)")
    private Double salary;

    @Column(columnDefinition = "datetime default current_timestamp")
    private LocalDate postingDate;
}
