package MedTechBackend.Backend.service.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocMembership;
import MedTechBackend.Backend.repository.Doctor.DocMembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocMembershipService {

    private final DocMembershipRepository docMembershipRepository;

    @Autowired
    public DocMembershipService(DocMembershipRepository docMembershipRepository) {
        this.docMembershipRepository = docMembershipRepository;
    }

    public void createDoctorMembership(DocMembership docMembership) {
        docMembershipRepository.save(docMembership);
    }

    public List<DocMembership> getAllDoctorsMembership() {
        return new ArrayList<>(docMembershipRepository.findAll());
    }

    public Optional<DocMembership> getDoctorMembershipById(Integer memid) {
        return docMembershipRepository.findById(memid);
    }

    public void deleteDoctorMembershipById(Integer memid) {
        docMembershipRepository.deleteById(memid);
    }

    public void deleteAllDoctorsMembership() {
        docMembershipRepository.deleteAll();
    }
}
