package MedTechBackend.Backend.repository.Patient;

import MedTechBackend.Backend.entity.Patient.Clinic;
import MedTechBackend.Backend.entity.Patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic,Integer> {
    List<Clinic> findByPatient(Patient patient);
    Clinic findByHospital(String hospital);
}