package peaksoft.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CreateCourseReq;
import peaksoft.dto.request.InstructorReq;
import peaksoft.dto.response.InstructorInfosResponse;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.models.Company;
import peaksoft.models.Course;
import peaksoft.models.Group;
import peaksoft.models.Instructor;
import peaksoft.repo.CompanyRepo;
import peaksoft.repo.CourseRepo;
import peaksoft.repo.InstructorRepo;
import peaksoft.service.InstructorService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepo instructorRepo;
    private final CompanyRepo companyRepo;
    private final CourseRepo courseRepo;
    private Instructor checkId(Long inId){
        return instructorRepo.findById(inId)
                .orElseThrow(() ->
                        new NoSuchElementException("Instructor with id: "+inId+" not found"));
    }
    @Override
    public InstructorResponse assigIn(Long companyId, InstructorReq instructorReq) {
        Company company = companyRepo.findById(companyId).orElseThrow(()->new EntityNotFoundException("Не найден!"));
        Instructor instructor  = new Instructor();
        instructor.setFirstName(instructorReq.getFirstName());
        instructor.setPhoneNumber(instructorReq.getPhoneNumber());
        instructor.setSpecialization(instructorReq.getSpecialization());
        instructor.setLastName(instructorReq.getLastName());
        company.getInstructors().add(instructor);

        company.getInstructors().add(instructor);
        instructor.getCompanies().add(company);
        instructorRepo.save(instructor);
        companyRepo.save(company);
        return InstructorResponse.builder().
                id(instructor.getId()).
                firstName(instructor.getFirstName())
                .lastName(instructor.getLastName())
                .specialization(instructor.getSpecialization())
                .phoneNumber(instructor.getPhoneNumber()).build();
    }

    @Override
    public Instructor findByInstructorIDi(Long instructorIdId) {
        return instructorRepo.findById(instructorIdId).orElseThrow(()->new EntityNotFoundException("Не найден!"));

    }

    @Override
    public List<Instructor> getAllInstructor() {
        return instructorRepo.findAll();
    }

    @Override
    public String updateInstructor(InstructorReq instructorReq, Long insId) {
        Instructor instructor = instructorRepo.findById(insId).orElseThrow(()->new EntityNotFoundException("Не найден!"));
instructor.setFirstName(instructorReq.getFirstName());
instructor.setLastName(instructorReq.getLastName());
instructor.setSpecialization(instructorReq.getSpecialization());
instructor.setPhoneNumber(instructorReq.getPhoneNumber());
instructorRepo.save(instructor);
        return "Updated!";
    }

    @Override
    public String deleteInsId(Long inseId) {
        Instructor instructor = instructorRepo.findById(inseId).orElseThrow(()->new EntityNotFoundException("Не найден!"));
        instructorRepo.delete(instructor);
        return "Deleted!";
    }

    @Override
    public int countStudent(Long instructorId) {
        return instructorRepo.countStudent(instructorId);
    }

    @Override
    public String assigInInsToCourse(Long courseId, Long instructorId) {
        Course course = courseRepo.findById(courseId).orElseThrow(()->new EntityNotFoundException("Курс не найден!"));
        Instructor instructor = instructorRepo.findById(instructorId).orElseThrow(()->new EntityNotFoundException("Инструктор не найден!"));
        course.setInstructor(instructor);
        instructor.getCourses().add(course);
        courseRepo.save(course);
        instructorRepo.save(instructor);

        return "succes";
    }

    @Override
    public InstructorInfosResponse instructorWIthInfos(Long inId) {
        Instructor instructor = checkId(inId);
        InstructorInfosResponse instructorInfosResponse = instructorRepo.instructorWIthInfos(inId);

        Map<String, Integer> groupNameWithStudents = instructorInfosResponse.getGroupNameWithStudent();
        List<Course> courses = instructor.getCourses();

        for (Course course : courses) {
            for (Group group : course.getGroups()) {
                int count = 0;
                count += group.getStudents().size();
                groupNameWithStudents.put(group.getGroupName(), count);
            }
        }
        return instructorInfosResponse;
    }
    }

