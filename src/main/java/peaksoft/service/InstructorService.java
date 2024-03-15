package peaksoft.service;

import peaksoft.dto.request.CreateCourseReq;
import peaksoft.dto.request.InstructorReq;
import peaksoft.dto.response.InstructorInfosResponse;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.models.Course;
import peaksoft.models.Instructor;

import java.util.List;

public interface InstructorService {
    InstructorResponse assigIn(Long companyId, InstructorReq instructorReq);


    Instructor findByInstructorIDi(Long instructorIdId);

    List<Instructor> getAllInstructor();

    String updateInstructor(InstructorReq instructorReq, Long insId);

    String deleteInsId(Long inseId);

    int countStudent(Long instructorId);

    String assigInInsToCourse(Long courseId, Long instructorId);

    InstructorInfosResponse instructorWIthInfos(Long inId);

}
