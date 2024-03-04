package peaksoft.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    @SequenceGenerator(name = "course-seq", sequenceName = "id-gen", allocationSize = 1)
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;

    //*************************************************
    @ManyToOne
    private Company company;
    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Group>groups;
    @ManyToOne
    private Instructor instructor;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Lesson>lessons;
//*************************************************


//*************************************************


}
