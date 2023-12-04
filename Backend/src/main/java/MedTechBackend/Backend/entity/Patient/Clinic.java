package MedTechBackend.Backend.entity.Patient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference(value = "patient-clinic")
    private Patient patient;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "clinic-report")
    private List<MedicalReport> reports;

    @Column(name = "admissionDate")
    private String admissionDate;

    @Column(name = "dischargeDate")
    private String dischargeDate;

    @Column(name = "attendingDoctor")
    private String attendingDoctor;

    @Column(name = "clinicRoom")
    private String clinicRoom;

    @Column(name = "hospital")
    private String hospital;

    @Column(name = "department")
    private String department;

}

