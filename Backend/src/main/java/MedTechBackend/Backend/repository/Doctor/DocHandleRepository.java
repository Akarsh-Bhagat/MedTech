package MedTechBackend.Backend.repository.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocHandle;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocHandleRepository extends JpaRepository<DocHandle,Integer> {
    List<DocHandle> findByDoctor(Doctors doctor);
}
