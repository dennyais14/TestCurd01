package co.develhope.TestCrud01.repositories;

import co.develhope.TestCrud01.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
