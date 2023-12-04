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

    @Column(name = "dateOfVisit")
    private LocalDate dateOfVisit;

    @Column(name = "reportDate")
    private LocalDate reportDate;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "symptoms")
    private String symptoms;

    @Column(name = "treatment")
    private String treatment;

    @Column(name = "prescription")
    private String prescription;

    @Column(name = "hospital")
    private String hospital;

}

