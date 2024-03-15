package peaksoft.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CreateCompanyReq;
import peaksoft.dto.request.GroupReq;
import peaksoft.dto.response.GroupResponse;
import peaksoft.models.Company;
import peaksoft.models.Group;
import peaksoft.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("api/group")
@RequiredArgsConstructor
public class GroupAPI {
    private final GroupService groupService;
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PostMapping("/CreatGroup/{courseId}")
    public ResponseEntity<?>createGroup(@RequestBody GroupReq groupReq,
                                        @PathVariable Long courseId){
    try {
        Group group = groupService.creatGroup(groupReq, courseId);
        return ResponseEntity.ok(group);
    }catch (RuntimeException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @GetMapping("/findById/{groupId}")
    public ResponseEntity<?> findById(@PathVariable Long groupId) {
        try {
            GroupResponse group = groupService.findByGroup(groupId);
            return ResponseEntity.ok(group);
//        } catch (NumberFormatException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Введите число");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/getAllGroup")
    public List<Group> getAllCompany() {
        return groupService.getAllGroup();
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")

    @PostMapping ("/updateGroupById/{GroupID}")
    public ResponseEntity<?> updateCompany(@RequestBody GroupReq groupReq,
                                           @PathVariable Long GroupID){

        try {
            groupService.upadetGroup(groupReq,GroupID);
            return ResponseEntity.ok("succes");

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());       }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @DeleteMapping("/delete/{groupId}")
    public String daleteGroupId(@PathVariable Long groupId){
        try {
            return groupService.deleteByCompanyId(groupId);
        }catch (EntityNotFoundException e){
            return e.getMessage();
        }
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/countStudent/{groupId}")
    public ResponseEntity<?>  countStudent(@PathVariable Long groupId){
       try {
           return ResponseEntity.ok(groupService.countStudentTogroup(groupId));
       }catch (EntityNotFoundException e){
         return   ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
       }
    }

}
