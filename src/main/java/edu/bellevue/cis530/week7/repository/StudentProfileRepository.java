package edu.bellevue.cis530.week7.repository;

import edu.bellevue.cis530.week7.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
}
