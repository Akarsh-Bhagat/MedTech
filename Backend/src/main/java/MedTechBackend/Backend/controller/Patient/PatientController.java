package MedTechBackend.Backend.controller.Patient;

import MedTechBackend.Backend.dto.Patient.PatientDTO;
import MedTechBackend.Backend.entity.Patient.Patient;
import MedTechBackend.Backend.service.Patient.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    private Patient convertDTOToEntity(PatientDTO patientDTO) {
        Patient patient=new Patient();
        patient.setId(patientDTO.getId());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setEmail(patientDTO.getEmail());
        patient.setContact(patientDTO.getContact());
        patient.setAddress(patientDTO.getAddress());
        patient.setGender(patientDTO.getGender());
        patient.setMedicalHistory(patientDTO.getMedicalHistory());
        return patient;
    }

    private PatientDTO convertEntityToDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setFirstName(patient.getFirstName());
        patientDTO.setLastName(patient.getLastName());
        patientDTO.setDateOfBirth(patient.getDateOfBirth());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setContact(patient.getContact());
        patientDTO.setGender(patient.getGender());
        return patientDTO;
    }

    @PostMapping
    public ResponseEntity<String> createNewPatient(@RequestBody Patient patient) {
        patientService.createPatient(patient);
        return new ResponseEntity<>("Patient created in the database", HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> docList = patientService.getAllPatient();
        return new ResponseEntity<>(docList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Integer id) {
        PatientDTO doc = patientService.getPatientById(id);
        Patient docEntity=convertDTOToEntity(doc);
        if (docEntity != null) {
            return new ResponseEntity<>(docEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Integer id, @RequestBody PatientDTO patientDTO) {
        Patient patientEntity = convertDTOToEntity(patientDTO);
        patientService.updatePatient(id, patientEntity);
        return new ResponseEntity<>("Patient details against ID " + id + " updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable Integer id) {
        if (patientService.deletePatientById(id)) {
            return new ResponseEntity<>("Patient deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllPatients() {
        patientService.deleteAllPatient();
        return new ResponseEntity<>("All patient deleted successfully", HttpStatus.OK);
    }
}
