package MedTechBackend.Backend.repository;

import MedTechBackend.Backend.entity.DocExperience;
import MedTechBackend.Backend.entity.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocExperienceRepository extends JpaRepository<DocExperience,Integer> {
    Optional<DocExperience> findByDoctor(Doctors doctors);
}
