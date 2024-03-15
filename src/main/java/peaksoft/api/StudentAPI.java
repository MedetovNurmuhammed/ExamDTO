package peaksoft.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CreateCompanyReq;
import peaksoft.dto.request.StudentReg;
import peaksoft.dto.response.StudentResponse;
import peaksoft.models.Company;
import peaksoft.models.Student;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentAPI {
    private final StudentService studentService;
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/creatStudent")
    public ResponseEntity<?> createStudent(@RequestBody StudentReg studentReg){
       try {
           StudentResponse student =  studentService.createStudent(studentReg);
           return ResponseEntity.ok(student);
       }catch (RuntimeException e){
           return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
       }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/findById/{studentId}")
    public ResponseEntity<?> findById(@PathVariable Long studentId) {
        try {
            Student student = studentService.findByStudent(studentId);
            return ResponseEntity.ok(student);
//        } catch (NumberFormatException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Введите число");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/getAllStudent")
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();
    }
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @PostMapping ("/updateStudentById/{studentID}")
    public ResponseEntity<?> updateCompany(@RequestBody StudentReg studentReg,
                                           @PathVariable Long studentID){

        try {
            studentService.updateStudent(studentReg,studentID);
            return ResponseEntity.ok("succes");

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());       }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")

    @DeleteMapping("/delete/{studentId}")
    public String deleteStudentId(@PathVariable Long studentId){
        try {
            return studentService.deleteByCompanyId(studentId);
        }catch (EntityNotFoundException e){
            return e.getMessage();
        }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/assignStudentToGroup/{groupId}/{studentId}")
    public ResponseEntity<?>assignin(@PathVariable Long groupId,
                                     @PathVariable  Long studentId){
       try {
           return ResponseEntity.ok(studentService.assigninTiGroup(groupId,studentId));
       }catch (EntityNotFoundException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
       }

    }
}

