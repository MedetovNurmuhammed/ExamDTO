package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CompanyWithInfo;
import peaksoft.models.Company;
import peaksoft.models.Student;

import java.util.List;

public interface CompanyRepo extends JpaRepository<Company,Long> {
    Boolean existsCompanyByName(String name);
@Query("select s from Student s where s.studyFormat ilike :format")
    List<Student> getStudyFormat(String format);
    @Query("select new peaksoft.dto.response.CompanyWithInfo(c.id, c.name, c.country, c.address, c.phoneNumber) from Company c where c.id =:companyId")
    CompanyWithInfo companyWithInfo(Long companyId);
}
