package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.response.InstructorInfosResponse;
import peaksoft.models.Instructor;

public interface InstructorRepo extends JpaRepository<Instructor,Long> {
    @Query("SELECT COUNT(s) FROM Student s inner join Course c on s.group.id =s.group.id where c.instructor.id= :instructorId")

    int countStudent (@Param("instructorId")Long instructorId);
    @Query("select new peaksoft.dto.response.InstructorInfosResponse(i.id, i.lastName, i.firstName, i.phoneNumber, i.specialization) from Instructor i where i.id =:inId")
    InstructorInfosResponse instructorWIthInfos(Long inId);
}
