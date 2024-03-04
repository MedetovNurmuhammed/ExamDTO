package peaksoft.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    @SequenceGenerator(name = "company-seq", sequenceName = "id-gen", allocationSize = 1)
    private Long id;
    private String name;
    private String country;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    //*************************************************
    @ManyToMany
    private List<Instructor> instructors;
    //*************************************************
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Course> courses;
//*************************************************


}
