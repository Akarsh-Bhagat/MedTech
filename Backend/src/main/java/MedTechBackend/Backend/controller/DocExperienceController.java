package MedTechBackend.Backend.controller;


import MedTechBackend.Backend.entity.DocExperience;
import MedTechBackend.Backend.service.DocExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
public class DocExperienceController {
    @Autowired
    private DocExperienceService docExperienceService;

    @PostMapping("/experiences")
    public String createNewDoctorExp(@RequestBody DocExperience docExperience) {
        docExperienceService.createDoctorExp(docExperience);
        return "Doctor experience created in the database";
    }

    @GetMapping("/experiences")
    public ResponseEntity<List<DocExperience>> getAllDoctorsExp() {
        List<DocExperience> expList = docExperienceService.getAllDoctorsExp();
        return new ResponseEntity<>(expList, HttpStatus.OK);
    }

    @GetMapping("/experiences/{expid}")
    public ResponseEntity<DocExperience> getDoctorsExpById(@PathVariable Integer expid) {
        Optional<DocExperience> exp = docExperienceService.getDoctorExpById(expid);
        return exp.map(docExperience -> new ResponseEntity<>(docExperience, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/experiences/{expid}")
    public String deleteDoctorsExpByExpId(@PathVariable Integer expid) {
        docExperienceService.deleteDoctorExpById(expid);
        return "Doctor experiences deleted successfully";
    }

    @DeleteMapping("/experiences")
    public String deleteAllDoctorsExp() {
        docExperienceService.deleteAllDoctorsExp();
        return "All doctors experiences deleted successfully";
    }

}
