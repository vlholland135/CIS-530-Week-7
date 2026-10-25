package edu.bellevue.cis530.week7.service.impl;

import edu.bellevue.cis530.week7.entity.Student;
import edu.bellevue.cis530.week7.entity.StudentProfile;
import edu.bellevue.cis530.week7.exception.ResourceNotFoundException;
import edu.bellevue.cis530.week7.repository.StudentRepository;
import edu.bellevue.cis530.week7.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        student.setId(null);
        if (student.getProfile() != null) {
            student.getProfile().setId(null);
            // keep both sides of the bidirectional relationship in sync
            student.getProfile().setStudent(student);
        }
        return studentRepository.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    @Override
    public Student updateStudent(Long id, Student updated) {
        Student existing = getStudentById(id);

        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setEmail(updated.getEmail());
        existing.setMajor(updated.getMajor());
        existing.setGpa(updated.getGpa());
        existing.setEnrollmentYear(updated.getEnrollmentYear());

        // update the existing profile in place so orphan removal is not triggered
        StudentProfile p = existing.getProfile();
        StudentProfile u = updated.getProfile();
        if (p == null) {
            u.setId(null);
            u.setStudent(existing);
            existing.setProfile(u);
        } else {
            p.setPhone(u.getPhone());
            p.setAddress(u.getAddress());
            p.setCity(u.getCity());
            p.setState(u.getState());
            p.setZip(u.getZip());
            p.setEmergencyContactName(u.getEmergencyContactName());
            p.setEmergencyContactPhone(u.getEmergencyContactPhone());
        }
        return studentRepository.save(existing);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentProfile getProfileByStudentId(Long id) {
        StudentProfile profile = getStudentById(id).getProfile();
        if (profile == null) {
            throw new ResourceNotFoundException("Profile not found for student id " + id);
        }
        return profile;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getStudentsByMajor(String major) {
        return studentRepository.findByMajor(major);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getStudentsByEnrollmentYear(Integer year) {
        return studentRepository.findByEnrollmentYear(year);
    }
}
