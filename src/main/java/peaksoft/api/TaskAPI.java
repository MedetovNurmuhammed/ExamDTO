package peaksoft.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.TaskReq;
import peaksoft.service.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/task")
public class TaskAPI {
    private final TaskService taskService;
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")

    @PostMapping("createTask/{lessonId}")
    public ResponseEntity<?>createTask(@PathVariable Long lessonId,
                                       @RequestBody TaskReq taskReq){
        try {
            return ResponseEntity.ok(taskService.createTask(lessonId,taskReq));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
