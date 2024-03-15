package peaksoft.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.forId.BaseEntity;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen", sequenceName = "stud_seq", allocationSize = 1)

public class Student extends BaseEntity {

    private Long id;
    private String fullName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String studyFormat;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    private Group group;
}
