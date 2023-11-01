package MedTechBackend.Backend.service;


import MedTechBackend.Backend.entity.DocExperience;
import MedTechBackend.Backend.repository.DocExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DocExperienceService {

    @Autowired
    DocExperienceRepository docExperienceRepository;

    public void createDoctorExp(DocExperience docExperience) {
        docExperienceRepository.save(docExperience);
    }

    public List<DocExperience> getAllDoctorsExp() {
        List<DocExperience> ExpList = new ArrayList<>();
        docExperienceRepository.findAll().forEach(ExpList::add);
        return ExpList;
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
