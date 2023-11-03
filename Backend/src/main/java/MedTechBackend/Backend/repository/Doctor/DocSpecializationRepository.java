package MedTechBackend.Backend.repository.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocSpecialization;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocSpecializationRepository extends JpaRepository<DocSpecialization,Integer> {
    List<DocSpecialization> findByDoctor(Doctors doctor);
}
