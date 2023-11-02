package MedTechBackend.Backend.controller;

import MedTechBackend.Backend.dto.DoctorsDTO;
import MedTechBackend.Backend.entity.DocExperience;
import MedTechBackend.Backend.service.DocExperienceService;
import MedTechBackend.Backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors/experiences")
public class DocExperienceController {

    private final DocExperienceService docExperienceService;

    private final DoctorService doctorService;

    public DocExperienceController(DocExperienceService docExperienceService, DoctorService doctorService) {
        this.docExperienceService = docExperienceService;
        this.doctorService = doctorService;
    }

//    public DocExperienceController(DocExperienceService docExperienceService, DoctorService doctorService) {
//        this.docExperienceService = docExperienceService;
//        this.doctorService = doctorService;
//    }

    @PostMapping("/{docid}")
    public ResponseEntity<String> createNewDoctorExp(@PathVariable Integer docid, @RequestBody DocExperience docExperience) {
        DoctorsDTO doctorDetails = doctorService.getDoctorById(docid);
        if (doctorDetails != null) {
            docExperience.setDoctor(doctorDetails.getDoctor());
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
