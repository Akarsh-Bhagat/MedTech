package MedTechBackend.Backend.entity.Appointment;

import MedTechBackend.Backend.entity.Doctor.Doctors;
import MedTechBackend.Backend.entity.Patient.Patient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

import java.util.Objects;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference(value = "patient-appointment")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonBackReference(value = "doctor-appointment")
    private Doctors doctor;

    @Column(name = "description")
    private String description;

    @Column(name = "appointment_time")
    private LocalDateTime appointmentTime;

    @Column(name = "accepted")
    private boolean accepted;

}