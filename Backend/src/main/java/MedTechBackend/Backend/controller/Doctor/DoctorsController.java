package MedTechBackend.Backend.controller.Doctor;

import MedTechBackend.Backend.dto.Doctor.DoctorsDTO;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import MedTechBackend.Backend.service.Doctor.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/doctors")
public class DoctorsController {

    private final DoctorService doctorService;

    public DoctorsController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    private Doctors convertDTOToEntity(DoctorsDTO doctorsDTO) {
        Doctors doctor=new Doctors();
        doctor.setId(doctorsDTO.getId());
        doctor.setFirstName(doctorsDTO.getFirstName());
        doctor.setLastName(doctorsDTO.getLastName());
        doctor.setDateOfBirth(doctorsDTO.getDateOfBirth());
        doctor.setEmail(doctorsDTO.getEmail());
        doctor.setSpecialisation(doctorsDTO.getSpecialisation());
        doctor.setAddress(doctorsDTO.getAddress());
        doctor.setExperiences(doctorsDTO.getExperiences());
        doctor.setAwards(doctorsDTO.getAwards());
        doctor.setEducation(doctorsDTO.getEducation());
        doctor.setMemberships(doctorsDTO.getMemberships());
        doctor.setSpecializations(doctorsDTO.getSpecializations());
        doctor.setServicings(doctorsDTO.getServicings());
        doctor.setHandles(doctorsDTO.getHandles());
        doctor.setRegistrations(doctorsDTO.getRegistrations());
        return doctor;
    }

    private DoctorsDTO convertEntityToDTO(Doctors doctors) {
        DoctorsDTO doctorsDTO = new DoctorsDTO();
        doctorsDTO.setId(doctors.getId());
        doctorsDTO.setFirstName(doctors.getFirstName());
        doctorsDTO.setLastName(doctors.getLastName());
        doctorsDTO.setDateOfBirth(doctors.getDateOfBirth());
        doctorsDTO.setEmail(doctors.getEmail());
        doctorsDTO.setSpecialisation(doctors.getSpecialisation());
        return doctorsDTO;
    }

    @PostMapping()
    public ResponseEntity<String> createNewDoctor(@RequestBody Doctors doctor) {
        doctorService.createDoctor(doctor);
        return new ResponseEntity<>("Doctor created in the database", HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Doctors>> getAllDoctors() {
        List<Doctors> docList = doctorService.getAllDoctors();
        return new ResponseEntity<>(docList, HttpStatus.OK);
    }


    @GetMapping("/{docid}")
    public ResponseEntity<Doctors> getDoctorById(@PathVariable Integer docid) {
        DoctorsDTO doc = doctorService.getDoctorById(docid);
        Doctors docEntity=convertDTOToEntity(doc);
        if (docEntity != null) {
            return new ResponseEntity<>(docEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{docid}")
    public ResponseEntity<String> updateDoctor(@PathVariable Integer docid, @RequestBody DoctorsDTO doctorsDTO) {
        Doctors doctorEntity = convertDTOToEntity(doctorsDTO);
        doctorService.updateDoctor(docid, doctorEntity);
        return new ResponseEntity<>("Doctor details against ID " + docid + " updated", HttpStatus.OK);
    }

    @DeleteMapping("/{docid}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable Integer docid) {
        if (doctorService.deleteDoctorById(docid)) {
            return new ResponseEntity<>("Doctor deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllDoctors() {
        doctorService.deleteAllDoctors();
        return new ResponseEntity<>("All doctors deleted successfully", HttpStatus.OK);
    }
}
