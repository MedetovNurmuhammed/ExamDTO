package peaksoft.dto.response;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public class CreateCourseResponse{
    private Long id;
   private String courseName;
   private LocalDate dateOfStart;
   private String description;
    }

