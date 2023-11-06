package MedTechBackend.Backend.service.Patient;

import MedTechBackend.Backend.entity.Patient.MedicalHistory;
import MedTechBackend.Backend.repository.Patient.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    public MedicalHistoryService(MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalHistoryRepository=medicalHistoryRepository;
    }

    public void createMedicalHistory(MedicalHistory medicalHistory) {
        medicalHistoryRepository.save(medicalHistory);
    }

    public List<MedicalHistory> getAllMedicalHistory() {
        return new ArrayList<>(medicalHistoryRepository.findAll());
    }

    public Optional<MedicalHistory> getMedicalHistoryById(Integer id) {
        return medicalHistoryRepository.findById(id);
    }

    public void deleteMedicalHistoryById(Integer id) {
        medicalHistoryRepository.deleteById(id);
    }

    public void deleteAllMedicalHistory() {
        medicalHistoryRepository.deleteAll();
    }
}
