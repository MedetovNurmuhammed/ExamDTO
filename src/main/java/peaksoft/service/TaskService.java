package peaksoft.service;

import org.springframework.http.ResponseEntity;
import peaksoft.dto.request.TaskReq;
import peaksoft.dto.response.LessonResponse;
import peaksoft.models.Lesson;

import java.time.LocalDate;

public interface TaskService {
    LessonResponse createTask(Long lessonId, TaskReq taskReq);

}
