package MedTechBackend.Backend.repository;

import MedTechBackend.Backend.entity.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorsRepository extends JpaRepository<Doctors, Integer> {
}
