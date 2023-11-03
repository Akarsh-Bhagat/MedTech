package MedTechBackend.Backend.repository.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocAward;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocAwardRepository extends JpaRepository<DocAward,Integer> {
    List<DocAward> findByDoctor(Doctors doctor);
}
