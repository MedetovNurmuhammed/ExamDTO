package peaksoft.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.forId.BaseEntity;

import java.time.ZonedDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen", sequenceName = "task_seq", allocationSize = 1)

public class Task extends BaseEntity {
    private String taskName;
    private String taskText;
    private ZonedDateTime deadLine;
    @JsonIgnore
    @ManyToOne
    private Lesson lesson;
}
