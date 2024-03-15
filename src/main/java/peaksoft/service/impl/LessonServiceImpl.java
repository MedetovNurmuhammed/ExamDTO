package peaksoft.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.LessonReq;
import peaksoft.models.Course;
import peaksoft.models.Instructor;
import peaksoft.models.Lesson;
import peaksoft.repo.CourseRepo;
import peaksoft.repo.LessonRepo;
import peaksoft.service.LessonService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepo lessonRepo;
    private final CourseRepo courseRepo;
    @Override
    public Lesson createLessonToCourse(Long courseId, LessonReq lessonReq) {
        Course course = courseRepo.findById(courseId).orElseThrow(()->new EntityNotFoundException("Курс не найден!"));
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonReq.getLessonName());
        course.getLessons().add(lesson);
        lesson.setCourse(course);
        courseRepo.save(course);
        lessonRepo.save(lesson);
        return lesson;
    }

    @Override
    public Lesson findByInstructorIDi(Long lessonId) {
        return lessonRepo.findById(lessonId).orElseThrow(()->new EntityNotFoundException("НЕ найден!"));

    }

    @Override
    public List<Lesson> getAllLesson() {
        return lessonRepo.findAll();
    }

    @Override
    public String updateLesson(LessonReq lessonReq, Long lessonId) {
        Lesson lesson = lessonRepo.findById(lessonId).orElseThrow(()->new EntityNotFoundException("НЕ найден!"));
        lesson.setLessonName(lessonReq.getLessonName());
        lessonRepo.save(lesson);
        return "Updated";

    }

    @Override
    public String deleteLesson(Long lessonId) {
        Lesson lesson = lessonRepo.findById(lessonId).orElseThrow(()->new EntityNotFoundException("НЕ найден!"));
        lessonRepo.delete(lesson);
        return "Deleted";
    }
}
