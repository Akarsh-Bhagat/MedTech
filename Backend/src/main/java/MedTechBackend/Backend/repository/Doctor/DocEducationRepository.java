package MedTechBackend.Backend.repository.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocEducation;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocEducationRepository extends JpaRepository<DocEducation,Integer> {
    List<DocEducation> findByDoctor(Doctors doctor);
}
