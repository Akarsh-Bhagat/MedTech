package MedTechBackend.Backend.repository.Patient;

import MedTechBackend.Backend.entity.Patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
}