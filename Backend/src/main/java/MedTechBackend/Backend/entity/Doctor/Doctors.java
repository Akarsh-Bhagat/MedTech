package MedTechBackend.Backend.entity.Doctor;
import MedTechBackend.Backend.entity.Appointment.Appointment;
import MedTechBackend.Backend.entity.Appointment.TimeSlot;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DocHandle> handles;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DocRegistration> registrations;

    @Column(name="is_available")
    @NotNull(message = "Please enter boolean value")
    private boolean isAvailable;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "doctor-appointment")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "doctor-timeSlot")
    private List<TimeSlot> timeSlots;


//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "availableDoctors")
//    @JsonIgnoreProperties(value = {"department", "availableDoctors", "bookedDoctors", "appointmentData", "appointment"}, allowSetters = true)
//    private Set<TimeSlot> availableTimeSlots = new HashSet<>();
//
//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "bookedDoctors")
//    @JsonIgnoreProperties(value = {"department", "availableDoctors", "bookedDoctors", "appointmentData", "appointment"}, allowSetters = true)
//    private Set<TimeSlot> bookedTimeSlots = new HashSet<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
//    @JsonIgnoreProperties(value = "doctor", allowSetters = true)
//    private List<Appointment> appointments;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
//    @JsonIgnoreProperties(value = "doctor", allowSetters = true)
//    private List<PostAppointmentData> postAppointmentData;


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
