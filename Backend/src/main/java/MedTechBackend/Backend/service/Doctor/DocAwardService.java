package MedTechBackend.Backend.service.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocAward;
import MedTechBackend.Backend.repository.Doctor.DocAwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocAwardService {

    private final DocAwardRepository docAwardRepository;

    @Autowired
    public DocAwardService(DocAwardRepository docAwardRepository) {
        this.docAwardRepository = docAwardRepository;
    }

    public void createDoctorAward(DocAward docAward) {
        docAwardRepository.save(docAward);
    }

    public List<DocAward> getAllDoctorsAward() {
        return new ArrayList<>(docAwardRepository.findAll());
    }

    public Optional<DocAward> getDoctorAwardById(Integer awdid) {
        return docAwardRepository.findById(awdid);
    }

    public void deleteDoctorAwardById(Integer awdid) {
        docAwardRepository.deleteById(awdid);
    }

    public void deleteAllDoctorsAward() {
        docAwardRepository.deleteAll();
    }
}
