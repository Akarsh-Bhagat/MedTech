package MedTechBackend.Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="doc_experience")
public class DocExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expId;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctors doctors;



}
