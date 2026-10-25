package edu.bellevue.cis530.week7.service;

import edu.bellevue.cis530.week7.entity.Student;
import edu.bellevue.cis530.week7.entity.StudentProfile;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
    StudentProfile getProfileByStudentId(Long id);
    List<Student> getStudentsByMajor(String major);
    List<Student> getStudentsByEnrollmentYear(Integer year);
}
