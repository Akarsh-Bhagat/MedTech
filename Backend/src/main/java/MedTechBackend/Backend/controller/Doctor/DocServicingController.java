package MedTechBackend.Backend.controller.Doctor;

import MedTechBackend.Backend.dto.Doctor.DoctorsDTO;
import MedTechBackend.Backend.entity.Doctor.DocServicing;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import MedTechBackend.Backend.service.Doctor.DocServicingService;
import MedTechBackend.Backend.service.Doctor.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors/servicings")
public class DocServicingController {

    private final DocServicingService docServicingService;

    private final DoctorService doctorService;

    public DocServicingController(DocServicingService docServicingService, DoctorService doctorService) {
        this.docServicingService = docServicingService;
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
    public ResponseEntity<String> createNewDoctorServicing(@PathVariable Integer docid, @RequestBody DocServicing docServicing) {
        DoctorsDTO doctorDetails = doctorService.getDoctorById(docid);
        Doctors doctorEntity=convertDTOToEntity(doctorDetails);
        if (doctorEntity != null) {
            docServicing.setDoctor(doctorEntity);
            docServicingService.createDoctorServicing(docServicing);
            return new ResponseEntity<>("Doctor service added in the database", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DocServicing>> getAllDoctorsServicing() {
        List<DocServicing> servicingList = docServicingService.getAllDoctorsServicing();
        return new ResponseEntity<>(servicingList, HttpStatus.OK);
    }

    @GetMapping("/{servid}")
    public ResponseEntity<DocServicing> getDoctorsServicingById(@PathVariable Integer servid) {
        return docServicingService.getDoctorServicingById(servid)
                .map(docServicing -> new ResponseEntity<>(docServicing, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{servid}")
    public ResponseEntity<String> deleteDoctorsServicingById(@PathVariable Integer servid) {
        docServicingService.deleteDoctorServicingById(servid);
        return new ResponseEntity<>("Doctor service removed successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllDoctorsServicing() {
        docServicingService.deleteAllDoctorsServicing();
        return new ResponseEntity<>("All doctors service removed successfully", HttpStatus.OK);
    }
}
