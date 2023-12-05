package MedTechBackend.Backend.controller.Doctor;

import MedTechBackend.Backend.dto.Doctor.DoctorsDTO;
import MedTechBackend.Backend.entity.Doctor.DocExperience;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import MedTechBackend.Backend.service.Doctor.DocExperienceService;
import MedTechBackend.Backend.service.Doctor.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors/experiences")
public class DocExperienceController {

    private final DocExperienceService docExperienceService;

    private final DoctorService doctorService;

    public DocExperienceController(DocExperienceService docExperienceService, DoctorService doctorService) {
        this.docExperienceService = docExperienceService;
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
    public ResponseEntity<String> createNewDoctorExp(@PathVariable Integer docid, @RequestBody DocExperience docExperience) {
        DoctorsDTO doctorDetails = doctorService.getDoctorById(docid);
        Doctors doctorEntity=convertDTOToEntity(doctorDetails);
        if (doctorEntity != null) {
            docExperience.setDoctor(doctorEntity);
            docExperienceService.createDoctorExp(docExperience);
            return new ResponseEntity<>("Doctor experience created in the database", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DocExperience>> getAllDoctorsExp() {
        List<DocExperience> expList = docExperienceService.getAllDoctorsExp();
        return new ResponseEntity<>(expList, HttpStatus.OK);
    }

    @GetMapping("/{expid}")
    public ResponseEntity<DocExperience> getDoctorsExpById(@PathVariable Integer expid) {
        return docExperienceService.getDoctorExpById(expid)
                .map(docExperience -> new ResponseEntity<>(docExperience, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{expid}")
    public ResponseEntity<String> deleteDoctorsExpByExpId(@PathVariable Integer expid) {
        docExperienceService.deleteDoctorExpById(expid);
        return new ResponseEntity<>("Doctor experiences deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllDoctorsExp() {
        docExperienceService.deleteAllDoctorsExp();
        return new ResponseEntity<>("All doctors experiences deleted successfully", HttpStatus.OK);
    }
}
