package peaksoft.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CreateCompanyReq;
import peaksoft.dto.request.CreateCourseReq;
import peaksoft.models.Company;
import peaksoft.models.Course;
import peaksoft.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseAPI {
    private final CourseService courseService;
@PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PostMapping("/create/{companyId}")
    public ResponseEntity<?> createCourse(@PathVariable Long companyId,
                                          @RequestBody CreateCourseReq createCourseReq) {
        try {
            Course course = courseService.createCourse(companyId, createCourseReq);
            return ResponseEntity.ok(course);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")

    @GetMapping("/findById/{courseId}")
    public ResponseEntity<?> findById(@PathVariable Long courseId) {
        try {
            Course course = courseService.findByCourseId(courseId);
            return ResponseEntity.ok(course);
//        } catch (NumberFormatException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Введите число");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @GetMapping("/getAllCourse")
    public List<Course> getAllCourse() {
        return courseService.getAllCourse();
    }
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")

    @PostMapping ("/CourseById/{courseId}")
    public ResponseEntity<?> updateCompany(@RequestBody CreateCourseReq createCourseReq,
                                           @PathVariable Long courseId){

        try {
            courseService.updateCompany(createCourseReq,courseId);
            return ResponseEntity.ok("succes");

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());       }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @DeleteMapping("/delete/{courseId}")
    public String deleteCompanyById(@PathVariable Long courseId){
        try {
            return courseService.deleteByCompanyId(courseId);
        }catch (EntityNotFoundException e){
            return e.getMessage();
        }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @GetMapping("/sortdatagetAll")
    public List<Course>sortCourese(){
        return courseService.getAllSort();

    }
}

