package edu.bellevue.cis530.week7.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Major is required")
    @Column(name = "major", nullable = false)
    private String major;

    @NotNull(message = "GPA is required")
    @DecimalMin(value = "0.0", message = "GPA must be at least 0.0")
    @DecimalMax(value = "4.0", message = "GPA must be no more than 4.0")
    @Column(name = "gpa", nullable = false, precision = 3, scale = 2)
    private BigDecimal gpa;

    // Update this value if the current year changes
    @NotNull(message = "Enrollment year is required")
    @Min(value = 2000, message = "Enrollment year must be 2000 or later")
    @Max(value = 2026, message = "Enrollment year cannot be in the future")
    @Column(name = "enrollment_year", nullable = false)
    private Integer enrollmentYear;

    @Valid
    @NotNull(message = "Profile is required")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @JsonManagedReference
    private StudentProfile profile;
}
