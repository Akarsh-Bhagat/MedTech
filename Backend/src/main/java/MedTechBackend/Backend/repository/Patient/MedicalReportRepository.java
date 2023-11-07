package MedTechBackend.Backend.repository.Patient;

import MedTechBackend.Backend.entity.Patient.MedicalReport;
import MedTechBackend.Backend.entity.Patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalReportRepository extends JpaRepository<MedicalReport,Integer> {
    List<MedicalReport> findByPatient(Patient patient);
}