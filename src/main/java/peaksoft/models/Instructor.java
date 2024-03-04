package peaksoft.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    @SequenceGenerator(name = "instructor-seq", sequenceName = "id-gen", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;
    @ManyToMany
    private List<Company>companies;
    @OneToMany
    private List<Course>courses;
}
