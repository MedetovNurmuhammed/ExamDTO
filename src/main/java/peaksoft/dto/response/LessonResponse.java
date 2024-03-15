package peaksoft.dto.response;

import lombok.Builder;
import peaksoft.models.Task;

import java.util.List;
@Builder
public record LessonResponse (Long id,String lessonName, List<Task>taskList){
}
