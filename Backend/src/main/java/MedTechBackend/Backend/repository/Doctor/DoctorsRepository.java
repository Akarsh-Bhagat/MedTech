package MedTechBackend.Backend.repository.Doctor;

import MedTechBackend.Backend.entity.Doctor.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorsRepository extends JpaRepository<Doctors, Integer> {
    @Query("SELECT DISTINCT d FROM Doctors d JOIN d.specializations s WHERE s.specialization = :specializationName")
    List<Doctors> findBySpecializationName(@Param("specializationName") List<String> specializationName);

}
