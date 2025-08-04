package com.spring.boot.springbootlab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "userId can not be empty")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "jobPostId can not be empty")
    @Column(columnDefinition = "int not null")
    private Integer jobPostId;

}
