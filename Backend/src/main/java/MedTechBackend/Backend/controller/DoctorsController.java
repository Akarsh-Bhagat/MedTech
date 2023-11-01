package MedTechBackend.Backend.controller;

        import MedTechBackend.Backend.dto.DoctorDetails;
        import MedTechBackend.Backend.service.DoctorService;
        import MedTechBackend.Backend.entity.Doctors;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;
        import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DoctorsController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/doctors")
    public String createNewDoctor(@RequestBody Doctors doctors) {
        doctorService.createDoctor(doctors);
        return "Doctor created in the database";
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctors>> getAllDoctors() {
        List<Doctors> docList = doctorService.getAllDoctors();
        return new ResponseEntity<>(docList, HttpStatus.OK);
    }

    @GetMapping("/doctors/{docid}")
    public ResponseEntity<DoctorDetails> getDoctorsById(@PathVariable Integer docid) {
        DoctorDetails doc = doctorService.getDoctorById(docid);
        return new ResponseEntity<>(doc, HttpStatus.OK);
    }

    @PutMapping("/doctors/{docid}")
    public ResponseEntity<String> updateDoctorsById(@PathVariable Integer docid, @RequestBody Doctors doctors) {
        doctorService.updateDoctor(docid, doctors);
        return new ResponseEntity<>("Doctor details against ID " + docid + " updated", HttpStatus.OK);
    }

    @DeleteMapping("/doctors/{docid}")
    public String deleteDoctorsByEmpId(@PathVariable Integer docid) {
        doctorService.deleteDoctorById(docid);
        return "Doctor deleted successfully";
    }

    @DeleteMapping("/doctors")
    public String deleteAllDoctors() {
        doctorService.deleteAllDoctors();
        return "All doctors deleted successfully";
    }
}



