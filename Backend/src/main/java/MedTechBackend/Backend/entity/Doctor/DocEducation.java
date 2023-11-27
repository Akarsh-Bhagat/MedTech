package MedTechBackend.Backend.entity.Doctor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Year;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doc_education")
public class DocEducation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "college")
    private String college;

    @Column(name= "degree")
    private String degree;

    @Column(name="yop")
    private Year yop;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonBackReference
    private Doctors doctor;


}
