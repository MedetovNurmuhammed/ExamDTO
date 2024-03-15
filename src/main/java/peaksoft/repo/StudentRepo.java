package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.models.Student;

public interface StudentRepo extends JpaRepository<Student,Long> {
    Boolean existsStudentByFullName(String name);
}
