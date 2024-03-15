package peaksoft.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.forId.BaseEntity;

import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen", sequenceName = "les_seq", allocationSize = 1)

public class Lesson extends BaseEntity {
    @SequenceGenerator(name = "base_id_gen", sequenceName = "les_seq", allocationSize = 1)
@Column(unique = true)
    private String lessonName;
    @JsonIgnore
    @ManyToOne
    private Course course;
    @OneToMany(mappedBy = "lesson")
    private List<Task>tasks;
}
