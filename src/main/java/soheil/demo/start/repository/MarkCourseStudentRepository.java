package soheil.demo.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soheil.demo.start.model.MarkCourseStudent;

@Repository
public interface MarkCourseStudentRepository extends JpaRepository<MarkCourseStudent, Long> {
}
