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
    @JsonBackReference
    private Patient patient;

    private List<String> pastConditions;

    private List<String> allergies;

    private List<String> surgeries;

    private List<String> medications;

    private String familyHistory;

    private String bloodGroup;

}

