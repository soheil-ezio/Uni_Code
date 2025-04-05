package soheil.demo.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soheil.demo.start.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {}
