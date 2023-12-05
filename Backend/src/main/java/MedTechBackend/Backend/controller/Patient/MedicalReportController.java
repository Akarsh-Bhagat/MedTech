package MedTechBackend.Backend.controller.Patient;


import MedTechBackend.Backend.dto.Patient.PatientDTO;
import MedTechBackend.Backend.entity.Patient.Clinic;
import MedTechBackend.Backend.entity.Patient.MedicalReport;
import MedTechBackend.Backend.entity.Patient.Patient;
import MedTechBackend.Backend.service.Patient.ClinicService;
import MedTechBackend.Backend.service.Patient.PatientService;
import MedTechBackend.Backend.service.Patient.MedicalReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/patient/medreport")
public class MedicalReportController {

    private final MedicalReportService medicalReportService;

    private final PatientService patientService;

    private final ClinicService clinicService;

    public MedicalReportController(MedicalReportService medicalReportService, PatientService patientService, ClinicService clinicService) {
        this.medicalReportService = medicalReportService;
        this.patientService = patientService;
        this.clinicService = clinicService;
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
    public ResponseEntity<String> createNewMedicalReport(@PathVariable Integer id, @RequestBody MedicalReport medicalReport) {
        PatientDTO patientDetails = patientService.getPatientById(id);
        Patient patientEntity=convertDTOToEntity(patientDetails);
        Clinic clinic = clinicService.getClinicByHospital(medicalReport.getHospital());
        if (patientEntity != null) {
            medicalReport.setPatient(patientEntity);
            medicalReport.setClinic(clinic);
            medicalReportService.createMedicalReport(medicalReport);
            return new ResponseEntity<>("Patient medical report added in the database", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<MedicalReport>> getAllMedicalReport() {
        List<MedicalReport> historyList = medicalReportService.getAllMedicalReport();
        return new ResponseEntity<>(historyList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalReport> getMedicalReportById(@PathVariable Integer id) {
        return medicalReportService.getMedicalReportById(id)
                .map(medicalReport -> new ResponseEntity<>(medicalReport, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicalReportById(@PathVariable Integer id) {
        medicalReportService.deleteMedicalReportById(id);
        return new ResponseEntity<>("Patient medical report removed successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllMedicalReport() {
        medicalReportService.deleteAllMedicalReport();
        return new ResponseEntity<>("All patients medical reports removed successfully", HttpStatus.OK);
    }
}
