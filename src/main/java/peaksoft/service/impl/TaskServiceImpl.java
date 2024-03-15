package peaksoft.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.TaskReq;
import peaksoft.dto.response.LessonResponse;
import peaksoft.models.Lesson;
import peaksoft.models.Task;
import peaksoft.repo.LessonRepo;
import peaksoft.repo.TaskRepo;
import peaksoft.service.TaskService;
@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final LessonRepo lessonRepo;
    @Override
    public LessonResponse createTask(Long lessonId, TaskReq taskReq) {
        Lesson lesson = lessonRepo.findById(lessonId).orElseThrow(()->new EntityNotFoundException("НЕ найден!"));
        Task task = new Task();
task.setTaskName(taskReq.getTaskName());
task.setTaskText(taskReq.getTaskText());
task.setDeadLine(taskReq.getDeadLine());
lesson.getTasks().add(task);
task.setLesson(lesson);
lessonRepo.save(lesson);
taskRepo.save(task);
        return LessonResponse.builder()
                .id(lesson.getId())
                .taskList(lesson.getTasks())
                .build();
    }
}
