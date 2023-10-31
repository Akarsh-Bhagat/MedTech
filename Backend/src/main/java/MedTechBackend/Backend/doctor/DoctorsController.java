package MedTechBackend.Backend.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController

@RequestMapping("/api")
public class DoctorsController {

    @Autowired
    DoctorsRepository doctorsRepository;

    @PostMapping("/doctors")
    public String createNewDoctor(@RequestBody Doctors doctors) {
        doctorsRepository.save(doctors);
        return "Doctor created in database";
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctors>> getAllDoctors() {
        List<Doctors> docList = new ArrayList<>();
        doctorsRepository.findAll().forEach(docList::add);
        return new ResponseEntity<List<Doctors>>(docList, HttpStatus.OK);
    }

    @GetMapping("/doctors/{docid}")
    public ResponseEntity<Doctors> getDoctorsById(@PathVariable Integer docid) {
        Optional<Doctors> doc = doctorsRepository.findById(docid);
        if (doc.isPresent()) {
            return new ResponseEntity<Doctors>(doc.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<Doctors>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/doctors/{docid}")
    public ResponseEntity<String> updateDoctorsById(@PathVariable Integer docid, @RequestBody Doctors doctors) {
        Optional<Doctors> doc = doctorsRepository.findById(docid);
        if (doc.isPresent()) {
            Doctors existDoc = doc.get();
            existDoc.setFirstname(doctors.getFirstname());
            existDoc.setLastname(doctors.getLastname());
            existDoc.setEmail(doctors.getEmail());
            existDoc.setAddress(doctors.getAddress());
            existDoc.setDob(doctors.getDob());
            existDoc.setSpecialisation(doctors.getSpecialisation());
            doctorsRepository.save(existDoc);
            return new ResponseEntity<>("Doctors Details against ID " + docid + " updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Doctors Details do not exist for docid " + docid, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/doctors/{docid}")
    public String deleteDoctorsByEmpId(@PathVariable Integer docid) {
        doctorsRepository.deleteById(docid);
        return "Employee Deleted Successfully";
    }

    @DeleteMapping("/doctors")
    public String deleteAllDoctors() {
        doctorsRepository.deleteAll();
        return "Doctors deleted Successfully..";
    }


}