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
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enrollment_id_generator")
    @SequenceGenerator(name = "enrollment_id_generator", sequenceName = "enrollment_seq", allocationSize = 1)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
