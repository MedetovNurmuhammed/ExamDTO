package peaksoft.service;

import org.springframework.http.ResponseEntity;
import peaksoft.dto.request.CreateCompanyReq;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CompanyWithInfo;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.models.Company;
import peaksoft.models.Student;

import java.util.List;


public interface CompanyService {
    SimpleResponse createCompany(CreateCompanyReq creatCompanyReq);


    Company findByCompanyId(Long companyId);

    List<Company> getAllCompany();

    String updateCompany(CreateCompanyReq company, Long companyID);

    String deleteByCompanyId(Long companyId);

    List<Student> getStudyFormat(Long companyId, String format);

    CompanyWithInfo companyWithInfo(Long companyId);
}
