package peaksoft.dto.response;

import lombok.Builder;
import peaksoft.models.Student;

import java.util.List;

@Builder
public record GroupResponse(Long id, String groupName,
                            String imageLink, String description, List<Student>studentList,int count) {
}
