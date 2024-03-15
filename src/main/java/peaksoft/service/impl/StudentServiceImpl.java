package peaksoft.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.StudentReg;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.StudentResponse;
import peaksoft.models.Group;
import peaksoft.models.Student;
import peaksoft.repo.GroupRepo;
import peaksoft.repo.StudentRepo;
import peaksoft.service.StudentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final GroupRepo groupRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public StudentResponse createStudent(StudentReg studentReg) {
        boolean exiatName = studentRepo.existsStudentByFullName(studentReg.getFullName());
        if (exiatName){
            throw new RuntimeException("Имя уже используется!");
        }
        Student student = new Student();
        student.setFullName(studentReg.getFullName());
        student.setLastName(studentReg.getLastName());
        student.setPhoneNumber(studentReg.getPhoneNumber());
        student.setEmail(studentReg.getEmail());
        student.setStudyFormat(studentReg.getStudyFormat());
        studentRepo.save(student);
        return StudentResponse.builder()
                .id(student.getId())
                .fullName(student.getFullName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .phoneNumber(student.getPhoneNumber())
                .studyFormat(student.getStudyFormat())
                .build();
    }

    @Override
    public Student findByStudent(Long studentId) {
        return studentRepo.findById(studentId).orElseThrow(()->new EntityNotFoundException("Не найден!"));
    }

    @Override
    public List<Student> getAllStudent() {
       return studentRepo.findAll();
    }

    @Override
    public String deleteByCompanyId(Long studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow(()->new EntityNotFoundException("Не найден!"));
        studentRepo.delete(student);
        return "Deleted!";
    }


    @Override
    public String updateStudent(StudentReg studentReg, Long studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow(()->new EntityNotFoundException("Не найден!"));
        student.setFullName(studentReg.getFullName());
        student.setLastName(studentReg.getLastName());
        student.setStudyFormat(studentReg.getStudyFormat());
        student.setPhoneNumber(studentReg.getPhoneNumber());
        student.setEmail(studentReg.getEmail());
        studentRepo.save(student);
        return "Updated!";
    }

    @Override
    public GroupResponse assigninTiGroup(Long groupId, Long studentId) {
        Group group = groupRepo.findById(groupId).orElseThrow(()->new EntityNotFoundException("Группа не существует!"));
        Student student = studentRepo.findById(studentId).orElseThrow(()->new EntityNotFoundException("Студент Не существует!"));
        group.getStudents().add(student);
        student.setGroup(group);
        groupRepo.save(group);
        studentRepo.save(student);
        return GroupResponse.builder()
                .groupName(group.getGroupName())
                .description(group.getDescription())
                .imageLink(group.getImageLink())
                .studentList(group.getStudents())
                .build();
    }
}
