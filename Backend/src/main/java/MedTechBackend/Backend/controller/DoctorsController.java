package MedTechBackend.Backend.controller;

import MedTechBackend.Backend.dto.DoctorsDTO;
import MedTechBackend.Backend.entity.Doctors;
import MedTechBackend.Backend.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/doctors")
public class DoctorsController {

    private final DoctorService doctorService;

    public DoctorsController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    private Doctors convertDTOToEntity(DoctorsDTO doctorsDTO) {
        Doctors doctors = new Doctors();

        // Perform the conversion from DoctorsDTO to Doctors
        doctors.setId(doctorsDTO.getId());
        doctors.setFirstName(doctorsDTO.getFirstName());
        doctors.setLastName(doctorsDTO.getLastName());
        doctors.setDateOfBirth(doctorsDTO.getDateOfBirth());
        doctors.setEmail(doctorsDTO.getEmail());
        doctors.setSpecialisation(doctorsDTO.getSpecialisation());
        doctors.setAddress(doctorsDTO.getAddress());
        // Set other fields as needed

        return doctors;
    }

    private DoctorsDTO convertEntityToDTO(Doctors doctors) {
        DoctorsDTO doctorsDTO = new DoctorsDTO();
        // Map fields from Doctors to DoctorsDTO
        doctorsDTO.setId(doctors.getId());
        doctorsDTO.setFirstName(doctors.getFirstName());
        doctorsDTO.setLastName(doctors.getLastName());
        doctorsDTO.setDateOfBirth(doctors.getDateOfBirth());
        doctorsDTO.setEmail(doctors.getEmail());
        doctorsDTO.setSpecialisation(doctors.getSpecialisation());
        // Map other fields as needed

        return doctorsDTO;
    }

    @PostMapping
    public ResponseEntity<String> createNewDoctor(@RequestBody DoctorsDTO doctorsDTO) {

        Doctors doctorEntity = convertDTOToEntity(doctorsDTO);
        doctorService.createDoctor(doctorEntity);
        return new ResponseEntity<>("Doctor created in the database", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DoctorsDTO>> getAllDoctors() {
        List<DoctorsDTO> docList = doctorService.getAllDoctors().stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(docList, HttpStatus.OK);
    }


    @GetMapping("/{docid}")
    public ResponseEntity<DoctorsDTO> getDoctorById(@PathVariable Integer docid) {
        DoctorsDTO doc = doctorService.getDoctorById(docid);
        if (doc != null) {
            DoctorsDTO doctorsDTO=convertEntityToDTO(doc.getDoctor());
            return new ResponseEntity<>(doctorsDTO, HttpStatus.OK);
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
