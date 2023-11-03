package MedTechBackend.Backend.repository.Doctor;

import MedTechBackend.Backend.entity.Doctor.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorsRepository extends JpaRepository<Doctors, Integer> {
}
