package peaksoft.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CreateCourseReq;
import peaksoft.dto.response.CreateCourseResponse;
import peaksoft.models.Company;
import peaksoft.models.Course;
import peaksoft.repo.CompanyRepo;
import peaksoft.repo.CourseRepo;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;
    private final CompanyRepo companyRepo;

    @Override
    public Course createCourse(Long companyId, CreateCourseReq createCourseReq) {
        Company company = companyRepo.findById(companyId).orElseThrow(()->new EntityNotFoundException("Не найден!"));
        Course courses = new Course();
        courses.setCourseName(createCourseReq.getCourseName());
        courses.setDescription(createCourseReq.getDescription());
        courses.setDateOfStart(createCourseReq.getDateOfStart());
        List<Course>courseList = new ArrayList<>();
        courseList.add(courses);
        company.setCourses(courseList);
        courses.setCompany(company);
      courseRepo.save(courses);
   return courses;
    }

    @Override
    public Course findByCourseId(Long courseId) {
        return courseRepo.findById(courseId).orElseThrow(()->new EntityNotFoundException("Не найден!"));
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepo.findAll();
    }

    @Override
    public String deleteByCompanyId(Long courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(()->new EntityNotFoundException("Не найден!"));
        courseRepo.delete(course);
        return "Успешно удален!";
    }

    @Override
    public String updateCompany(CreateCourseReq createCourseReq, Long courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(()->new EntityNotFoundException("Не найден!"));
        course.setCourseName(createCourseReq.getCourseName());
        course.setDateOfStart(createCourseReq.getDateOfStart());
        course.setDescription(createCourseReq.getDescription());
        courseRepo.save(course);
        return "Updated";
    }

    @Override
    public List<Course> getAllSort() {
        return courseRepo.getAllSort();
    }
}
