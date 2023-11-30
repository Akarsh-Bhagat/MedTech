package MedTechBackend.Backend.service.Doctor;

import MedTechBackend.Backend.entity.Doctor.DocHandle;
import MedTechBackend.Backend.repository.Doctor.DocHandleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocHandleService {

    private final DocHandleRepository docHandleRepository;

    @Autowired
    public DocHandleService(DocHandleRepository docHandleRepository) {
        this.docHandleRepository = docHandleRepository;
    }

    public void createDoctorHandle(DocHandle docHandle) {
        docHandleRepository.save(docHandle);
    }

    public List<DocHandle> getAllDoctorsHandles() {
        return new ArrayList<>(docHandleRepository.findAll());
    }

    public Optional<DocHandle> getDoctorHandleById(Integer awdid) {
        return docHandleRepository.findById(awdid);
    }

    public void deleteDoctorHandleById(Integer awdid) {
        docHandleRepository.deleteById(awdid);
    }

    public void deleteAllDoctorsHandle() {
        docHandleRepository.deleteAll();
    }
}
