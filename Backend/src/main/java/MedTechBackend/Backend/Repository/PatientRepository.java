package MedTechBackend.Backend.Repository;

import MedTechBackend.Backend.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Optional<Patient> findByPatientEmailIdAndPatientPassword(String EmailId, String Password);
    public List<Patient> findPatientByDoctorId(long doctorId);


}