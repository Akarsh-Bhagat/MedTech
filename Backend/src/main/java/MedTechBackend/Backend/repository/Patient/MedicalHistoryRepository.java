package MedTechBackend.Backend.repository.Patient;

import MedTechBackend.Backend.entity.Patient.MedicalHistory;
import MedTechBackend.Backend.entity.Patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory,Integer> {
    MedicalHistory findByPatient(Patient patient);
}