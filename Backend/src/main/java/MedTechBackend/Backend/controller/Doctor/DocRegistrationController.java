package MedTechBackend.Backend.controller.Doctor;

import MedTechBackend.Backend.dto.Doctor.DoctorsDTO;
import MedTechBackend.Backend.entity.Doctor.DocRegistration;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import MedTechBackend.Backend.service.Doctor.DocRegistrationService;
import MedTechBackend.Backend.service.Doctor.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors/registrations")
public class DocRegistrationController {

    private final DocRegistrationService docRegistrationService;

    private final DoctorService doctorService;

    public DocRegistrationController(DocRegistrationService docRegistrationService, DoctorService doctorService) {
        this.docRegistrationService = docRegistrationService;
        this.doctorService = doctorService;
    }


    private Doctors convertDTOToEntity(DoctorsDTO doctorsDTO) {
        Doctors doctors = new Doctors();
        doctors.setId(doctorsDTO.getId());
        doctors.setFirstName(doctorsDTO.getFirstName());
        doctors.setLastName(doctorsDTO.getLastName());
        doctors.setDateOfBirth(doctorsDTO.getDateOfBirth());
        doctors.setEmail(doctorsDTO.getEmail());
        doctors.setSpecialisation(doctorsDTO.getSpecialisation());
        doctors.setAddress(doctorsDTO.getAddress());
        return doctors;
    }

    @PostMapping("/{docid}")
    public ResponseEntity<String> createNewDoctorRegistration(@PathVariable Integer docid, @RequestBody DocRegistration docRegistration) {
        DoctorsDTO doctorDetails = doctorService.getDoctorById(docid);
        Doctors doctorEntity=convertDTOToEntity(doctorDetails);
        if (doctorEntity != null) {
            docRegistration.setDoctor(doctorEntity);
            docRegistrationService.createDoctorRegistration(docRegistration);
            return new ResponseEntity<>("Doctor registration added in the database", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DocRegistration>> getAllDoctorsRegistration() {
        List<DocRegistration> registrationList = docRegistrationService.getAllDoctorsRegistration();
        return new ResponseEntity<>(registrationList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocRegistration> getDoctorsRegistrationById(@PathVariable Integer id) {
        return docRegistrationService.getDoctorRegistrationById(id)
                .map(docRegistration -> new ResponseEntity<>(docRegistration, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctorsRegistrationById(@PathVariable Integer id) {
        docRegistrationService.deleteDoctorRegistrationById(id);
        return new ResponseEntity<>("Doctor registrations removed successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllDoctorsRegistration() {
        docRegistrationService.deleteAllDoctorsRegistration();
        return new ResponseEntity<>("All doctors registrations removed successfully", HttpStatus.OK);
    }
}
