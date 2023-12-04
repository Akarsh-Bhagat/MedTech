package MedTechBackend.Backend.service.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocRegistration;
import MedTechBackend.Backend.repository.Doctor.DocRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocRegistrationService {

    private final DocRegistrationRepository docRegistrationRepository;

    @Autowired
    public DocRegistrationService(DocRegistrationRepository docRegistrationRepository) {
        this.docRegistrationRepository = docRegistrationRepository;
    }

    public void createDoctorRegistration(DocRegistration docRegistration) {
        docRegistrationRepository.save(docRegistration);
    }

    public List<DocRegistration> getAllDoctorsRegistration() {
        return new ArrayList<>(docRegistrationRepository.findAll());
    }

    public Optional<DocRegistration> getDoctorRegistrationById(Integer awdid) {
        return docRegistrationRepository.findById(awdid);
    }

    public void deleteDoctorRegistrationById(Integer awdid) {
        docRegistrationRepository.deleteById(awdid);
    }

    public void deleteAllDoctorsRegistration() {
        docRegistrationRepository.deleteAll();
    }
}
