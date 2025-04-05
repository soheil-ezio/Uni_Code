package soheil.demo.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soheil.demo.start.model.UniClass;

@Repository
public interface UniClassRepository extends JpaRepository<UniClass, Long> {
}
