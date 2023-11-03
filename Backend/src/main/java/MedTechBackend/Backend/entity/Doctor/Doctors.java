package MedTechBackend.Backend.entity.Doctor;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctors")
public class Doctors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "doc_firstname",nullable = false)
    private String firstName;

    @Column(name = "doc_lastname",nullable = false)
    private String lastName;

    @Column(name = "doc_email",unique = true,nullable = false)
    private String email;

    @Column(name = "doc_address",nullable = false)
    private String address;

    @Column(name = "doc_dob")
    private LocalDate dateOfBirth;

    @Column(name = "doc_spec")
    private String specialisation;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DocExperience> experiences;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DocAward> awards;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DocEducation> education;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DocMembership> memberships;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DocServicing> servicings;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DocSpecialization> specializations;

    @Override
    public String toString() {
        return "Doctors{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", specialisation='" + specialisation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Doctors doctors = (Doctors) obj;
        return getEmail() != null ? getEmail().equals(doctors.getEmail()) : doctors.getEmail() == null;
    }

    @Override
    public int hashCode() {
        return getEmail() != null ? getEmail().hashCode() : 0;
    }
}
