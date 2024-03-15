package peaksoft.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import peaksoft.models.Course;
import peaksoft.models.Instructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompanyReq {
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
private List<Course> course;
private List<Instructor> instructors;
}
