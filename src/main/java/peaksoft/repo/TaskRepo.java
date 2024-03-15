package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.models.Task;

public interface TaskRepo extends JpaRepository<Task,Long> {
}
