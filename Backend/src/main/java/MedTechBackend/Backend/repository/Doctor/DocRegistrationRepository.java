package MedTechBackend.Backend.repository.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocRegistration;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocRegistrationRepository extends JpaRepository<DocRegistration,Integer> {
    List<DocRegistration> findByDoctor(Doctors doctor);
}
