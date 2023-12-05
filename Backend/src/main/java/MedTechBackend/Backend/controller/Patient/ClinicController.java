package MedTechBackend.Backend.controller.Patient;


import MedTechBackend.Backend.dto.Patient.PatientDTO;
import MedTechBackend.Backend.entity.Patient.Clinic;
import MedTechBackend.Backend.entity.Patient.Patient;
import MedTechBackend.Backend.service.Patient.PatientService;
import MedTechBackend.Backend.service.Patient.ClinicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/patient/clinic")
public class ClinicController {

    private final ClinicService clinicService;

    private final PatientService patientService;

    public ClinicController(ClinicService clinicService, PatientService patientService) {
        this.clinicService = clinicService;
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
    public ResponseEntity<String> createNewClinic(@PathVariable Integer id, @RequestBody Clinic clinic) {
        PatientDTO patientDetails = patientService.getPatientById(id);
        Patient patientEntity=convertDTOToEntity(patientDetails);
        if (patientEntity != null) {
            clinic.setPatient(patientEntity);
            clinicService.createClinic(clinic);
            return new ResponseEntity<>("Clinic added in the database", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Clinic>> getAllClinic() {
        List<Clinic> historyList = clinicService.getAllClinic();
        return new ResponseEntity<>(historyList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clinic> getClinicById(@PathVariable Integer id) {
        return clinicService.getClinicById(id)
                .map(clinic -> new ResponseEntity<>(clinic, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClinicById(@PathVariable Integer id) {
        clinicService.deleteClinicById(id);
        return new ResponseEntity<>("Clinic removed successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllClinic() {
        clinicService.deleteAllClinic();
        return new ResponseEntity<>("All clinics removed successfully", HttpStatus.OK);
    }
}
