package MedTechBackend.Backend.entity.Patient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medical_history")
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference(value = "patient-medreport")
    private Patient patient;

    @Column(name = "pastConditions")
    private List<String> pastConditions;

    @Column(name = "allergies")
    private List<String> allergies;

    @Column(name = "surgeries")
    private List<String> surgeries;

    @Column(name = "medications")
    private List<String> medications;

    @Column(name = "familyHistory")
    private String familyHistory;

    @Column(name = "bloodGroup")
    private String bloodGroup;

}

