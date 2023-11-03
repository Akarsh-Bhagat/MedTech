package MedTechBackend.Backend.repository.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocServicing;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocServicingRepository extends JpaRepository<DocServicing,Integer> {
    List<DocServicing> findByDoctor(Doctors doctor);
}
