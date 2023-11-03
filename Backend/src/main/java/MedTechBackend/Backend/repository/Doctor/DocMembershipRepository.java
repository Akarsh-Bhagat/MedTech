package MedTechBackend.Backend.repository.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocMembership;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocMembershipRepository extends JpaRepository<DocMembership,Integer> {
    List<DocMembership> findByDoctor(Doctors doctor);
}
