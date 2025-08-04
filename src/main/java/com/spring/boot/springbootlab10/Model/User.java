package com.spring.boot.springbootlab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "age >= 22")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name must not be empty")
    @Size(min = 5, message = "name must not be less than 5 characters")
    @Pattern(regexp = "^\\D+$", message = "name must not contain numbers") // not a number
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @Email(message = "email must be valid")
    @Column(columnDefinition = "varchar(30) unique")
    private String email;

    @NotEmpty(message = "password must not be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;

    @NotNull(message = "age can not be empty")
    @Positive(message = "age must be a positive number")
    @Min(value = 22, message = "age must not be younger than 22")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "role can not be empty")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$", message = "role must be either JOB_SEEKER or EMPLOYER")
    @Column(columnDefinition = "varchar(11) not null")
    private String role;

}
