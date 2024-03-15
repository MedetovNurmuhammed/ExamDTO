package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.models.Course;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course,Long> {
    @Query("select c from Course  c order by c.dateOfStart asc ")
    List<Course> getAllSort();
}
