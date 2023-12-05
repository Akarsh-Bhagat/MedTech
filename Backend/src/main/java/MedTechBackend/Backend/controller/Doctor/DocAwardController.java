package MedTechBackend.Backend.controller.Doctor;

import MedTechBackend.Backend.dto.Doctor.DoctorsDTO;
import MedTechBackend.Backend.entity.Doctor.DocAward;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import MedTechBackend.Backend.service.Doctor.DocAwardService;
import MedTechBackend.Backend.service.Doctor.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors/awards")
public class DocAwardController {

    private final DocAwardService docAwardService;

    private final DoctorService doctorService;

    public DocAwardController(DocAwardService docAwardService, DoctorService doctorService) {
        this.docAwardService = docAwardService;
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
    public ResponseEntity<String> createNewDoctorAward(@PathVariable Integer docid, @RequestBody DocAward docAward) {
        DoctorsDTO doctorDetails = doctorService.getDoctorById(docid);
        Doctors doctorEntity=convertDTOToEntity(doctorDetails);
        if (doctorEntity != null) {
            docAward.setDoctor(doctorEntity);
            docAwardService.createDoctorAward(docAward);
            return new ResponseEntity<>("Doctor award added in the database", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DocAward>> getAllDoctorsAward() {
        List<DocAward> awardList = docAwardService.getAllDoctorsAward();
        return new ResponseEntity<>(awardList, HttpStatus.OK);
    }

    @GetMapping("/{awdid}")
    public ResponseEntity<DocAward> getDoctorsAwardById(@PathVariable Integer awdid) {
        return docAwardService.getDoctorAwardById(awdid)
                .map(docAward -> new ResponseEntity<>(docAward, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{awdid}")
    public ResponseEntity<String> deleteDoctorsAwardByAwdId(@PathVariable Integer awdid) {
        docAwardService.deleteDoctorAwardById(awdid);
        return new ResponseEntity<>("Doctor awards removed successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllDoctorsAward() {
        docAwardService.deleteAllDoctorsAward();
        return new ResponseEntity<>("All doctors awards removed successfully", HttpStatus.OK);
    }
}
