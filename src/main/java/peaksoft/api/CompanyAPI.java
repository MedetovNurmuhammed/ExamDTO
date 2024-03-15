package peaksoft.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CreateCompanyReq;
import peaksoft.dto.response.CompanyWithInfo;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.models.Company;
import peaksoft.models.Student;
import peaksoft.service.CompanyService;

import java.util.InputMismatchException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyAPI {
    private final CompanyService companyService;
@PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/create")
    public SimpleResponse creatCompany(@RequestBody CreateCompanyReq creatCompanyReq) {
        try {
           return companyService.createCompany(creatCompanyReq);
        } catch (RuntimeException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.CONFLICT).message(e.getMessage()).build();
        }
        // companyService.createCompany(creatCompanyReq);
    }
@PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @GetMapping("/findById/{companyId}")
    public ResponseEntity<?> findById(@PathVariable Long companyId) {
        try {
            Company company = companyService.findByCompanyId(companyId);
            return ResponseEntity.ok(company);
//        } catch (NumberFormatException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Введите число");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
@PreAuthorize("hasAnyAuthority('ADMIN')")//несколько роль жазса болот
    @GetMapping("/getAllCompany")
    public List<Company> getAllCompany() {
        return companyService.getAllCompany();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")//1 эле жазылат!
    @PostMapping ("/updateCompanyById/{companyID}")
    public ResponseEntity<?> updateCompany(@RequestBody CreateCompanyReq company,
                                          @PathVariable Long companyID){

       try {
            companyService.updateCompany(company,companyID);
           return ResponseEntity.ok("succes");

       }catch (EntityNotFoundException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());       }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete/{companyId}")
    public String deleteCompanyById(@PathVariable Long companyId){
      try {
          return companyService.deleteByCompanyId(companyId);
      }catch (EntityNotFoundException e){
          return e.getMessage();
      }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/studyFormat/{companyId}/{format}")
    public ResponseEntity<?>stadyFormat(@PathVariable Long companyId,
                                    @PathVariable String format){
      try {
          return ResponseEntity.ok(companyService.getStudyFormat(companyId,format));
      }catch (EntityNotFoundException e){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/CompanyWithInfo/{companyId}")
    public ResponseEntity<?> companyWithInfo(@PathVariable Long companyId){
      try {  return ResponseEntity.ok(companyService.companyWithInfo(companyId));
    }catch (EntityNotFoundException e){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }
    }
    // Компанияны чакырганда толук маалыматы, курстардын аты,
    // группалардын аты, инструкторлордун аты, бардык студенттердин саны чыксын.
}