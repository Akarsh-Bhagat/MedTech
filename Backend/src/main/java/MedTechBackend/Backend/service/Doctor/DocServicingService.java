package MedTechBackend.Backend.service.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocServicing;
import MedTechBackend.Backend.repository.Doctor.DocServicingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocServicingService {

    private final DocServicingRepository docServicingRepository;

    @Autowired
    public DocServicingService(DocServicingRepository docServicingRepository) {
        this.docServicingRepository = docServicingRepository;
    }

    public void createDoctorServicing(DocServicing docServicing) {
        docServicingRepository.save(docServicing);
    }

    public List<DocServicing> getAllDoctorsServicing() {
        return new ArrayList<>(docServicingRepository.findAll());
    }

    public Optional<DocServicing> getDoctorServicingById(Integer servid) {
        return docServicingRepository.findById(servid);
    }

    public void deleteDoctorServicingById(Integer servid) {
        docServicingRepository.deleteById(servid);
    }

    public void deleteAllDoctorsServicing() {
        docServicingRepository.deleteAll();
    }
}
