package edu.bellevue.cis530.week7.repository;

import edu.bellevue.cis530.week7.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByMajor(String major);
    List<Student> findByEnrollmentYear(Integer enrollmentYear);
}
