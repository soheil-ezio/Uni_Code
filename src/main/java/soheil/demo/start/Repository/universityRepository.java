package soheil.demo.start.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soheil.demo.start.model.university;

@Repository
public interface universityRepository extends JpaRepository<university, Long> {
}
