package MedTechBackend.Backend.entity.Doctor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doc_award")
public class DocAward{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title",unique = true)
    private String title;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonBackReference
    private Doctors doctor;


}
