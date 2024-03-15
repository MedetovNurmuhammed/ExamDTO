package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.models.Group;

public interface GroupRepo extends JpaRepository<Group,Long> {
    @Query("select case when count(g) > 0 then true else false end from Group g where lower(g.groupName) like lower( :groupName)")
    boolean existsGroupByName(@Param("groupName") String groupName);
    @Query("SELECT COUNT(s) FROM Student s WHERE s.group.id = :groupId")
    int countStudentTogroup(@Param("groupId")Long groupId);}

