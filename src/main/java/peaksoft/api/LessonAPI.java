package peaksoft.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.InstructorReq;
import peaksoft.dto.request.LessonReq;
import peaksoft.models.Instructor;
import peaksoft.models.Lesson;
import peaksoft.service.InstructorService;
import peaksoft.service.LessonService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/lesson")
public class LessonAPI {
    private final LessonService lessonService;
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @PostMapping("/createLesson/{courseId}")
    public ResponseEntity<?> createLesson(@PathVariable Long courseId,
                                          @RequestBody LessonReq lessonReq) {
        try {
            return ResponseEntity.ok(lessonService.createLessonToCourse(courseId, lessonReq));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/findById/{lessonId}")
    public ResponseEntity<?> findById(@PathVariable Long lessonId) {
        try {

            Lesson lesson = lessonService.findByInstructorIDi(lessonId);
            return ResponseEntity.ok(lesson);
//        } catch (NumberFormatException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Введите число");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")

    @GetMapping("/getAllLesson")
    public List<Lesson> getAllCourse() {
        return lessonService.getAllLesson();
    }
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")

    @PostMapping ("/update/{lessonId}")
    public ResponseEntity<?> updateInstructor(@RequestBody LessonReq lessonReq,
                                              @PathVariable Long lessonId){

        try {

            return ResponseEntity.ok(lessonService.updateLesson(lessonReq, lessonId));

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());       }
    }
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @DeleteMapping("/delete/{lessonId}")
    public String deleteLessonById(@PathVariable Long lessonId){
        try {
            return lessonService.deleteLesson(lessonId);
        }catch (EntityNotFoundException e){
            return e.getMessage();
        }
}}