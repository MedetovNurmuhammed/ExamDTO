package peaksoft.service;

import org.springframework.http.ResponseEntity;
import peaksoft.dto.request.GroupReq;
import peaksoft.dto.response.GroupResponse;
import peaksoft.models.Company;
import peaksoft.models.Group;

import java.util.List;

public interface GroupService  {
    Group creatGroup(GroupReq groupReq, Long courseId);

    GroupResponse findByGroup(Long groupId);

    List<Group> getAllGroup();

    String upadetGroup(GroupReq groupReq, Long groupID);

    String deleteByCompanyId(Long groupId);

    int countStudentTogroup(Long groupId);
}
