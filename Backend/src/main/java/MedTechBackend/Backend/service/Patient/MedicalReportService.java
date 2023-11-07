package MedTechBackend.Backend.service.Patient;

import MedTechBackend.Backend.entity.Patient.MedicalReport;
import MedTechBackend.Backend.repository.Patient.MedicalReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalReportService {

    private final MedicalReportRepository medicalReportRepository;

    @Autowired
    public MedicalReportService(MedicalReportRepository medicalReportRepository) {
        this.medicalReportRepository = medicalReportRepository;
    }


    public void createMedicalReport(MedicalReport medicalReport) {
        medicalReportRepository.save(medicalReport);
    }

    public List<MedicalReport> getAllMedicalReport() {
        return new ArrayList<>(medicalReportRepository.findAll());
    }

    public Optional<MedicalReport> getMedicalReportById(Integer id) {
        return medicalReportRepository.findById(id);
    }

    public void deleteMedicalReportById(Integer id) {
        medicalReportRepository.deleteById(id);
    }

    public void deleteAllMedicalReport() {
        medicalReportRepository.deleteAll();
    }
}
