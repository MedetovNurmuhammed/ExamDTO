package peaksoft.service;

import org.springframework.http.ResponseEntity;
import peaksoft.dto.request.StudentReg;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.StudentResponse;
import peaksoft.models.Group;
import peaksoft.models.Student;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentReg studentReg);

    Student findByStudent(Long studentId);

    List<Student> getAllStudent();

    String deleteByCompanyId(Long studentId);

    String updateStudent(StudentReg studentReg, Long studentId);

    GroupResponse assigninTiGroup(Long groupId, Long studentId);

//    Group assigninTiGroup(Long groupId, Long studentId);
}
