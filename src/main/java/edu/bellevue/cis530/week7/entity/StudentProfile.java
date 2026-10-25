package edu.bellevue.cis530.week7.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_profiles")
@Getter
@Setter
@NoArgsConstructor
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Phone must match the format 123-456-7890")
    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @NotBlank(message = "State is required")
    @Size(min = 2, max = 2, message = "State must be exactly 2 characters")
    @Column(name = "state", length = 2)
    private String state;

    @NotBlank(message = "Zip is required")
    @Pattern(regexp = "^\\d{5}$", message = "Zip must be exactly 5 digits")
    @Column(name = "zip", length = 5)
    private String zip;

    @NotBlank(message = "Emergency contact name is required")
    @Column(name = "emergency_contact_name")
    private String emergencyContactName;

    @NotBlank(message = "Emergency contact phone is required")
    @Column(name = "emergency_contact_phone")
    private String emergencyContactPhone;

    @OneToOne(mappedBy = "profile")
    @JsonBackReference
    private Student student;
}
