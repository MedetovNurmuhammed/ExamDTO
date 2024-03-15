package peaksoft.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.forId.BaseEntity;


import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen", sequenceName = "com_seq", allocationSize = 1)

public class Company extends BaseEntity {
    @Column(unique = true)
    private String name;
    private String country;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    //*************************************************
    @ManyToMany(mappedBy = "companies",cascade = CascadeType.PERSIST)
    private List<Instructor> instructors;
    //*************************************************
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "company")
    private List<Course> courses;
//*************************************************


}
