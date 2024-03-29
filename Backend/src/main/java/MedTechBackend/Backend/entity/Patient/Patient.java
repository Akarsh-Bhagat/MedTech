package MedTechBackend.Backend.entity.Patient;
import MedTechBackend.Backend.entity.Appointment.Appointment;
import MedTechBackend.Backend.entity.Doctor.DocExperience;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstName",nullable = false)
    private String firstName;

    @Column(name = "lastName",nullable = false)
    private String lastName;

    @Column(name = "gender",nullable = false)
    private String gender;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "dob")
    private LocalDate dateOfBirth;

    @Column(name = "contact",nullable = false)
    private String contact;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "patient-medreport")
    private MedicalHistory medicalHistory;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "patient-report")
    private List<MedicalReport> reports;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "patient-clinic")
    private List<Clinic> clinics;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "patient-appointment")
    private Appointment appointment;


//
//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<DocAward> awards;
//
//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<DocEducation> education;
//
//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<DocMembership> memberships;
//
//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<DocServicing> servicings;
//
//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<DocSpecialization> specializations;

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", contact='" + contact + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        Patient patient = (Patient) obj;
//        return getEmail() != null ? getEmail().equals(patient.getEmail()) : patient.getEmail() == null;
//    }
//
//    @Override
//    public int hashCode() {
//        return getEmail() != null ? getEmail().hashCode() : 0;
//    }
}
