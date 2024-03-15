package peaksoft.service;

import peaksoft.dto.request.CreateCourseReq;
import peaksoft.dto.response.CreateCourseResponse;
import peaksoft.models.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(Long companyId, CreateCourseReq createCourseReq);

    Course findByCourseId(Long courseId);

    List<Course> getAllCourse();

    String deleteByCompanyId(Long courseId);

    String updateCompany(CreateCourseReq createCourseReq, Long courseId);

    List<Course> getAllSort( );
}
