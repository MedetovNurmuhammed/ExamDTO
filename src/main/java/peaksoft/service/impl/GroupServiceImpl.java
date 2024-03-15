package peaksoft.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.GroupReq;
import peaksoft.dto.response.GroupResponse;
import peaksoft.models.Course;
import peaksoft.models.Group;
import peaksoft.repo.CourseRepo;
import peaksoft.repo.GroupRepo;
import peaksoft.service.GroupService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepo groupRepo;
    private final CourseRepo courseRepo;

    @Override
    public Group creatGroup(GroupReq groupReq, Long courseId) {
        boolean existName = groupRepo.existsGroupByName(groupReq.getGroupName());
        if (existName){
throw new RuntimeException("Имя уже существует!");        }
        Group group = new Group();
        group.setGroupName(groupReq.getGroupName());
        group.setDescription(groupReq.getDescription());
        group.setImageLink(groupReq.getImageLink());
        Course course = courseRepo.findById(courseId).orElseThrow(()->new EntityNotFoundException("Course Не найден!"));
        group.getCourses().add(course);
        course.getGroups().add(group);
       return groupRepo.save(group);
    }

    @Override
    public GroupResponse findByGroup(Long groupId) {
Group group = groupRepo.findById(groupId).orElseThrow(()->new EntityNotFoundException("НЕ найден!"));
return GroupResponse.builder()
        .groupName(group.getGroupName())
        .description(group.getDescription())
        .imageLink(group.getImageLink())
        .studentList(group.getStudents())
        .build();

    }

    @Override
    public List<Group> getAllGroup() {
      return   groupRepo.findAll();
    }

    @Override
    public String upadetGroup(GroupReq groupReq, Long groupID) {
        Group group = groupRepo.findById(groupID).orElseThrow(()->new EntityNotFoundException("Не найден!"));
        group.setGroupName(groupReq.getGroupName());
        group.setDescription(groupReq.getDescription());
        group.setImageLink(groupReq.getImageLink());
        groupRepo.save(group);
        return "Updated";
    }

    @Override
    public String deleteByCompanyId(Long groupId) {
        Group group = groupRepo.findById(groupId).orElseThrow(()->new EntityNotFoundException("Не найден!"));
groupRepo.delete(group);
    return "Успешно удален!";}

    @Override
    public int countStudentTogroup(Long groupId) {
        Group group = groupRepo.findById(groupId).orElseThrow(()-> new EntityNotFoundException("НЕ найден!"));
   return groupRepo.countStudentTogroup(groupId);

    }}

