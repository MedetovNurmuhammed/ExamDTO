package peaksoft.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CreateCompanyReq;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CompanyWithInfo;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.models.*;
import peaksoft.repo.CompanyRepo;
import peaksoft.service.CompanyService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepo companyRepo;
    private Company checkId(Long companyId) {
        return companyRepo.findById(companyId)
                .orElseThrow(() ->
                        new NoSuchElementException("Company with id: " + companyId + " not found"));
    }
    @Override
    public SimpleResponse createCompany(CreateCompanyReq creatCompanyReq) {
        boolean exiatName = companyRepo.existsCompanyByName(creatCompanyReq.getName());
        if (exiatName){
            throw new RuntimeException("Имя уже используется!");
        }
        Company company = new Company();
        company.setName(creatCompanyReq.getName());
        company.setCountry(creatCompanyReq.getCountry());
        company.setPhoneNumber(creatCompanyReq.getPhoneNumber());
        company.setAddress(creatCompanyReq.getAddress());
        companyRepo.save(company);
        return SimpleResponse.builder().
                httpStatus(HttpStatus.OK)
                .message("Успешно Сохранен!").build();
    }

    @Override
    public Company findByCompanyId(Long companyId) {
        return companyRepo.findById(companyId).orElseThrow(()->new EntityNotFoundException("Не найден!"));

    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepo.findAll();
    }

    @Override
    public String updateCompany(CreateCompanyReq company, Long companyID) {
        Company exist = companyRepo.findById(companyID).orElseThrow(()->new EntityNotFoundException("НЕ найден!"));
        exist.setName(company.getName());
        exist.setCountry(company.getCountry());
        exist.setAddress(company.getAddress());
        exist.setPhoneNumber(company.getPhoneNumber());
        exist.setCourses(company.getCourse());
        exist.setInstructors(company.getInstructors());

        companyRepo.save(exist);
        return "succes";

    }

    @Override
    public String deleteByCompanyId(Long companyId) {
        Company company = companyRepo.findById(companyId).orElseThrow(()->new EntityNotFoundException("Не найден!"));
        companyRepo.delete(company);
        return "Успешно удален!";
    }

    @Override
    public List<Student> getStudyFormat(Long companyId, String format) {
        Company company = companyRepo.findById(companyId).orElseThrow(()->new EntityNotFoundException("Не найден!"));
      return   companyRepo.getStudyFormat(format);
    }

    @Override
    public CompanyWithInfo companyWithInfo(Long companyId) {
        Company company = checkId(companyId);

        CompanyWithInfo companyWithInfo = companyRepo.companyWithInfo(companyId);


        for (Instructor instructor : company.getInstructors()) {
            companyWithInfo.addInstructorsName(instructor.getFirstName());
        }
        int count = 0;
        for (Course course : company.getCourses()) {
            companyWithInfo.addCourseNames(course.getCourseName());
            for (Group group : course.getGroups()) {
                companyWithInfo.addGroupName(group.getGroupName());
                count += group.getStudents().size();
            }
        }
        companyWithInfo.setCountOfStudents(count);
        return companyWithInfo;
    }
    }




