package peaksoft.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.forId.BaseEntity;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen", sequenceName = "group_seq", allocationSize = 1)

public class Group extends BaseEntity {
    @SequenceGenerator(name = "base_id_gen", sequenceName = "group_seq", allocationSize = 1)
@Column(unique = true)
    private String groupName;
    private String imageLink;
    private String description;
    @ManyToMany
    private List<Course>courses = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.REMOVE,CascadeType.MERGE},mappedBy = "group")
    private List<Student>students;
}
