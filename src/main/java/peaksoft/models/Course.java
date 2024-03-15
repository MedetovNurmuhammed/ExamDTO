package peaksoft.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.forId.BaseEntity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen", sequenceName = "cor_seq", allocationSize = 1)

public class Course extends BaseEntity {

    @SequenceGenerator(name = "base_id_gen", sequenceName = "cor_seq", allocationSize = 1)

    private String courseName;
    private LocalDate dateOfStart;
    private String description;

    //*************************************************
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    private Company company;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REMOVE,mappedBy = "courses")
    private List<Group>groups =new ArrayList<>();
    @ManyToOne
    @JsonIgnore
    private Instructor instructor;
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "course")
    private List<Lesson>lessons = new ArrayList<>();
//*************************************************


//*************************************************


}
