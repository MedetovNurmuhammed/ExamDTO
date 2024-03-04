package peaksoft.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    @SequenceGenerator(name = "student-seq", sequenceName = "id-gen", allocationSize = 1)
    private Long id;
    private String fullName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String studyFormat;

    @ManyToOne
    private Group group;
}
