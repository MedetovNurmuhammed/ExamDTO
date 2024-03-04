package peaksoft.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    @SequenceGenerator(name = "lesson-seq", sequenceName = "id-gen", allocationSize = 1)
    private Long id;
    private String lessonName;
    @ManyToOne
    private Course course;
}
