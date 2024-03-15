package peaksoft.service;

import org.springframework.http.ResponseEntity;
import peaksoft.dto.request.LessonReq;
import peaksoft.models.Instructor;
import peaksoft.models.Lesson;

import java.util.List;

public interface LessonService {
    Lesson createLessonToCourse(Long courseId, LessonReq lessonReq);

    Lesson findByInstructorIDi(Long lessonId);

    List<Lesson> getAllLesson();

    String updateLesson(LessonReq lessonReq, Long lessonId);

    String deleteLesson(Long lessonId);
}
