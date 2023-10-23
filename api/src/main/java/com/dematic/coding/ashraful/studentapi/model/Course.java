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
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_generator")
    @SequenceGenerator(name = "course_id_generator", sequenceName = "course_seq", allocationSize = 1)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    private String courseName;
    private String courseCode;
    private String courseDescription;

}
