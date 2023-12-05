package MedTechBackend.Backend.controller.Doctor;

import MedTechBackend.Backend.dto.Doctor.DoctorsDTO;
import MedTechBackend.Backend.entity.Doctor.DocHandle;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import MedTechBackend.Backend.service.Doctor.DocHandleService;
import MedTechBackend.Backend.service.Doctor.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors/handles")
public class DocHandleController {

    private final DocHandleService docHandleService;

    private final DoctorService doctorService;

    public DocHandleController(DocHandleService docHandleService, DoctorService doctorService) {
        this.docHandleService = docHandleService;
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
    public ResponseEntity<String> createNewDoctorAward(@PathVariable Integer docid, @RequestBody DocHandle docHandle) {
        DoctorsDTO doctorDetails = doctorService.getDoctorById(docid);
        Doctors doctorEntity=convertDTOToEntity(doctorDetails);
        if (doctorEntity != null) {
            docHandle.setDoctor(doctorEntity);
            docHandleService.createDoctorHandle(docHandle);
            return new ResponseEntity<>("Doctor social media handle added in the database", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DocHandle>> getAllDoctorsHandles() {
        List<DocHandle> handleList = docHandleService.getAllDoctorsHandles();
        return new ResponseEntity<>(handleList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocHandle> getDoctorsHandleById(@PathVariable Integer id) {
        return docHandleService.getDoctorHandleById(id)
                .map(docHandle -> new ResponseEntity<>(docHandle, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctorsHandleById(@PathVariable Integer id) {
        docHandleService.deleteDoctorHandleById(id);
        return new ResponseEntity<>("Doctor handle removed successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllDoctorsHandle() {
        docHandleService.deleteAllDoctorsHandle();
        return new ResponseEntity<>("All doctors handles removed successfully", HttpStatus.OK);
    }
}
