package MedTechBackend.Backend.Repository;

import java.util.Optional;

import MedTechBackend.Backend.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Optional<Doctor> findByDoctorEmailIdAndDoctorPassword(String emailId,String password);

}
