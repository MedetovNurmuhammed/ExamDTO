package peaksoft.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.forId.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen", sequenceName = "ins_seq", allocationSize = 1)

public class Instructor extends BaseEntity {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Company>companies = new ArrayList<>();
    @OneToMany(mappedBy = "instructor",cascade = CascadeType.MERGE)
    private List<Course>courses;
}
