package MedTechBackend.Backend.service.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocExperience;
import MedTechBackend.Backend.repository.Doctor.DocExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocExperienceService {

    private final DocExperienceRepository docExperienceRepository;

    @Autowired
    public DocExperienceService(DocExperienceRepository docExperienceRepository) {
        this.docExperienceRepository = docExperienceRepository;
    }

    public void createDoctorExp(DocExperience docExperience) {
        docExperienceRepository.save(docExperience);
    }

    public List<DocExperience> getAllDoctorsExp() {
        return new ArrayList<>(docExperienceRepository.findAll());
    }

    public Optional<DocExperience> getDoctorExpById(Integer expid) {
        return docExperienceRepository.findById(expid);
    }

    public void deleteDoctorExpById(Integer expid) {
        docExperienceRepository.deleteById(expid);
    }

    public void deleteAllDoctorsExp() {
        docExperienceRepository.deleteAll();
    }
}
