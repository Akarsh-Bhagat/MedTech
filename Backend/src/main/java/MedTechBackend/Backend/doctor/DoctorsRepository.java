package MedTechBackend.Backend.doctor;

import MedTechBackend.Backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface DoctorsRepository extends JpaRepository<Doctors, Integer> {
}
