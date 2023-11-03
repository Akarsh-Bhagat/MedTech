package MedTechBackend.Backend.service.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocEducation;
import MedTechBackend.Backend.repository.Doctor.DocEducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocEducationService {

    private final DocEducationRepository docEducationRepository;

    @Autowired
    public DocEducationService(DocEducationRepository docEducationRepository) {
        this.docEducationRepository = docEducationRepository;
    }

    public void createDoctorEd(DocEducation docEducation) {
        docEducationRepository.save(docEducation);
    }

    public List<DocEducation> getAllDoctorsEd() {
        return new ArrayList<>(docEducationRepository.findAll());
    }

    public Optional<DocEducation> getDoctorEdById(Integer edid) {
        return docEducationRepository.findById(edid);
    }

    public void deleteDoctorEdById(Integer edid) {
        docEducationRepository.deleteById(edid);
    }

    public void deleteAllDoctorsEd() {
        docEducationRepository.deleteAll();
    }
}
