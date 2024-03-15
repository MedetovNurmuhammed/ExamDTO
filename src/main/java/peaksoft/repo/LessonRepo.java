package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.models.Lesson;

public interface LessonRepo extends JpaRepository<Lesson,Long> {
}
