package MedTechBackend.Backend.controller.Patient;


import MedTechBackend.Backend.dto.Patient.PatientDTO;
import MedTechBackend.Backend.entity.Patient.MedicalHistory;
import MedTechBackend.Backend.entity.Patient.Patient;
import MedTechBackend.Backend.service.Patient.PatientService;
import MedTechBackend.Backend.service.Patient.MedicalHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient/medhistory")
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;

    private final PatientService patientService;

    public MedicalHistoryController(MedicalHistoryService medicalHistoryService, PatientService patientService) {
        this.medicalHistoryService = medicalHistoryService;
        this.patientService = patientService;
    }


    private Patient convertDTOToEntity(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setId(patientDTO.getId());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setEmail(patientDTO.getEmail());
        patient.setContact(patientDTO.getContact());
        patient.setAddress(patientDTO.getAddress());
        patient.setGender(patientDTO.getGender());
        return patient;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> createNewMedicalHistory(@PathVariable Integer id, @RequestBody MedicalHistory medicalHistory) {
        PatientDTO patientDetails = patientService.getPatientById(id);
        Patient patientEntity=convertDTOToEntity(patientDetails);
        if (patientEntity != null) {
            medicalHistory.setPatient(patientEntity);
            medicalHistoryService.createMedicalHistory(medicalHistory);
            return new ResponseEntity<>("Patient medical history added in the database", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<MedicalHistory>> getAllMedicalHistory() {
        List<MedicalHistory> historyList = medicalHistoryService.getAllMedicalHistory();
        return new ResponseEntity<>(historyList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistory> getMedicalHistoryById(@PathVariable Integer id) {
        return medicalHistoryService.getMedicalHistoryById(id)
                .map(medicalHistory -> new ResponseEntity<>(medicalHistory, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicalHistoryById(@PathVariable Integer id) {
        medicalHistoryService.deleteMedicalHistoryById(id);
        return new ResponseEntity<>("Patient medical history removed successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllMedicalHistory() {
        medicalHistoryService.deleteAllMedicalHistory();
        return new ResponseEntity<>("All patient medical history removed successfully", HttpStatus.OK);
    }
}
