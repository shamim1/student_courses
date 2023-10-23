package com.dematic.coding.ashraful.studentapi.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_generator")
    @SequenceGenerator(name = "student_id_generator", sequenceName = "student_seq", allocationSize = 1)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    private String firstName;
    private String lastName;

}
