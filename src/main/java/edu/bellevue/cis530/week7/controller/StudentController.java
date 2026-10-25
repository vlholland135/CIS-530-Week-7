package edu.bellevue.cis530.week7.controller;

import edu.bellevue.cis530.week7.entity.Student;
import edu.bellevue.cis530.week7.entity.StudentProfile;
import edu.bellevue.cis530.week7.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(Map.of("message", "Student and profile deleted successfully"));
    }

    @GetMapping("/{id}/profile")
    public StudentProfile getProfile(@PathVariable Long id) {
        return studentService.getProfileByStudentId(id);
    }

    @GetMapping("/major/{major}")
    public List<Student> getByMajor(@PathVariable String major) {
        return studentService.getStudentsByMajor(major);
    }

    @GetMapping("/year/{year}")
    public List<Student> getByYear(@PathVariable Integer year) {
        return studentService.getStudentsByEnrollmentYear(year);
    }
}
