package MedTechBackend.Backend.service.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocSpecialization;
import MedTechBackend.Backend.repository.Doctor.DocSpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocSpecializationService {

    private final DocSpecializationRepository docSpecializationRepository;

    
    @Autowired
    public DocSpecializationService(DocSpecializationRepository docSpecializationRepository) {
        this.docSpecializationRepository = docSpecializationRepository;
    }

    public void createDoctorSpecialization(DocSpecialization docSpecialization) {
        docSpecializationRepository.save(docSpecialization);
    }

    public List<DocSpecialization> getAllDoctorsSpecialization() {
        return new ArrayList<>(docSpecializationRepository.findAll());
    }

    public Optional<DocSpecialization> getDoctorSpecializationById(Integer spid) {
        return docSpecializationRepository.findById(spid);
    }

    public void deleteDoctorSpecializationById(Integer spid) {
        docSpecializationRepository.deleteById(spid);
    }

    public void deleteAllDoctorsSpecialization() {
        docSpecializationRepository.deleteAll();
    }
}
