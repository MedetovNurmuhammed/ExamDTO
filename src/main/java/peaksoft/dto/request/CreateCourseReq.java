package peaksoft.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class CreateCourseReq {
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;

}

