package MedTechBackend.Backend.entity.Patient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference(value = "clinic-report")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference(value = "patient-report")
    private Patient patient;

    private LocalDate dateOfVisit;
    private LocalDate reportDate;
    private String diagnosis;
    private String symptoms;
    private String treatment;
    private String prescription;
    private String hospital;

}

