package MedTechBackend.Backend.entity.Doctor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Year;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doc_experience")
public class DocExperience{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hospital")
    private String hospital;

    @Column(name="city")
    private String city;

    @Column(name="description")
    private String description;

    @Column(name="startYear")
    private Year startYear;

    @Column(name="endYear")
    private Year endYear;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonBackReference
    private Doctors doctor;


}
