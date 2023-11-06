package MedTechBackend.Backend.entity.Patient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient_clinic")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference
    private Patient patient;

    @OneToMany(mappedBy = "clinic")
    @JsonManagedReference
    private List<MedicalReport> reports;

    private String admissionDate;

    private String dischargeDate;

    private String attendingDoctor;

    private String clinicRoom;

    private String hospital;

    private String department;

}

