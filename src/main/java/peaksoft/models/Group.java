package peaksoft.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    @SequenceGenerator(name = "group-seq", sequenceName = "id-gen", allocationSize = 1)
    private Long id;
    private String groupName;
    private String imageLink;
    private String description;
    @ManyToMany
    private List<Course>courses;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Student>students;
}
