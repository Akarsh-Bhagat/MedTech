package MedTechBackend.Backend.controller.Doctor;

import MedTechBackend.Backend.dto.Doctor.DoctorsDTO;
import MedTechBackend.Backend.entity.Doctor.DocEducation;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import MedTechBackend.Backend.service.Doctor.DocEducationService;
import MedTechBackend.Backend.service.Doctor.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors/education")
public class DocEducationController {

    private final DocEducationService docEducationService;

    private final DoctorService doctorService;

    public DocEducationController(DocEducationService docEducationService, DoctorService doctorService) {
        this.docEducationService = docEducationService;
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
    public ResponseEntity<String> createNewDoctorEducation(@PathVariable Integer docid, @RequestBody DocEducation docEducation) {
        DoctorsDTO doctorDetails = doctorService.getDoctorById(docid);
        Doctors doctorEntity=convertDTOToEntity(doctorDetails);
        if (doctorEntity != null) {
            docEducation.setDoctor(doctorEntity);
            docEducationService.createDoctorEd(docEducation);
            return new ResponseEntity<>("Doctor education added in the database", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DocEducation>> getAllDoctorsEducation() {
        List<DocEducation> educationList = docEducationService.getAllDoctorsEd();
        return new ResponseEntity<>(educationList, HttpStatus.OK);
    }

    @GetMapping("/{edid}")
    public ResponseEntity<DocEducation> getDoctorsEducationById(@PathVariable Integer edid) {
        return docEducationService.getDoctorEdById(edid)
                .map(docEducation -> new ResponseEntity<>(docEducation, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{edid}")
    public ResponseEntity<String> deleteDoctorsEducationByAwdId(@PathVariable Integer edid) {
        docEducationService.deleteDoctorEdById(edid);
        return new ResponseEntity<>("Doctor educations removed successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllDoctorsEducation() {
        docEducationService.deleteAllDoctorsEd();
        return new ResponseEntity<>("All doctors educations removed successfully", HttpStatus.OK);
    }
}
