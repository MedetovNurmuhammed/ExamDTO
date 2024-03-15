package peaksoft.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CreateCourseReq;
import peaksoft.dto.request.InstructorReq;
import peaksoft.dto.response.InstructorInfosResponse;
import peaksoft.models.Instructor;
import peaksoft.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")
@RequiredArgsConstructor
public class InstructorAPI {
    private final InstructorService instructorService;
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("assignIn/{companyId}")
    public ResponseEntity<?> assignIn(@PathVariable Long companyId,
                                   @RequestBody InstructorReq instructorReq){
       try {
           return ResponseEntity.ok(instructorService.assigIn(companyId,instructorReq));
       }catch (EntityNotFoundException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
       }

    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("assignIn/{courseId}/{instructorId}")
    public ResponseEntity<?> assignInCourse(@PathVariable Long courseId,
                                      @PathVariable Long instructorId){
        try {
            return ResponseEntity.ok(instructorService.assigInInsToCourse(courseId,instructorId));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @GetMapping("/findById/{InstructorIdId}")
    public ResponseEntity<?> findById(@PathVariable Long InstructorIdId) {
        try {
            Instructor instructor = instructorService.findByInstructorIDi(InstructorIdId);
            return ResponseEntity.ok(instructor);
//        } catch (NumberFormatException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Введите число");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/getAllInstructor")
    public List<Instructor> getAllCourse() {
        return instructorService.getAllInstructor();
    }
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @PostMapping ("/insId/{insId}")
    public ResponseEntity<?> updateInstructor(@RequestBody InstructorReq instructorReq,
                                           @PathVariable Long insId){

        try {
            instructorService.updateInstructor(instructorReq, insId);
            return ResponseEntity.ok("succes");

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());       }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")

    @DeleteMapping("/delete/{InseId}")
    public String deleteCompanyById(@PathVariable Long InseId){
        try {
            return instructorService.deleteInsId(InseId);
        }catch (EntityNotFoundException e){
            return e.getMessage();
        }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")

    @GetMapping("/InstructorWithInfos/{inId}")
    public InstructorInfosResponse instructorWithInfos(@PathVariable Long inId){
        return instructorService.instructorWIthInfos(inId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/countStudent/{instructorId}")
    public int countStudent(@PathVariable Long instructorId){
        return instructorService.countStudent(instructorId);
    }
}

