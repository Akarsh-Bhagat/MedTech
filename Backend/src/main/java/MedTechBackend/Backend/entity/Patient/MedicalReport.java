package MedTechBackend.Backend.entity.Patient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medical_report")
public class MedicalReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    @JsonBackReference
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference
    private Patient patient;

    private LocalDate dateOfVisit;
    private LocalDate reportDate;
    private String diagnosis;
    private String symptoms;
    private String treatment;
    private String prescription;

}

