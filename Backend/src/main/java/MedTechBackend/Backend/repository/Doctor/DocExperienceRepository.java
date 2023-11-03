package MedTechBackend.Backend.repository.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocExperience;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocExperienceRepository extends JpaRepository<DocExperience,Integer> {
    List<DocExperience> findByDoctor(Doctors doctor);
}
