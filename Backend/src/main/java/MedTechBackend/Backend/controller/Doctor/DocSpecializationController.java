package MedTechBackend.Backend.controller.Doctor;

import MedTechBackend.Backend.dto.Doctor.DoctorsDTO;
import MedTechBackend.Backend.entity.Doctor.DocSpecialization;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import MedTechBackend.Backend.service.Doctor.DocSpecializationService;
import MedTechBackend.Backend.service.Doctor.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors/specializations")
public class DocSpecializationController {

    private final DocSpecializationService docSpecializationService;

    private final DoctorService doctorService;

    public DocSpecializationController(DocSpecializationService docSpecializationService, DoctorService doctorService) {
        this.docSpecializationService = docSpecializationService;
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
    public ResponseEntity<String> createNewDoctorSpecialization(@PathVariable Integer docid, @RequestBody DocSpecialization docSpecialization) {
        DoctorsDTO doctorDetails = doctorService.getDoctorById(docid);
        Doctors doctorEntity=convertDTOToEntity(doctorDetails);
        if (doctorEntity != null) {
            docSpecialization.setDoctor(doctorEntity);
            docSpecializationService.createDoctorSpecialization(docSpecialization);
            return new ResponseEntity<>("Doctor specialization added in the database", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DocSpecialization>> getAllDoctorsSpecialization() {
        List<DocSpecialization> specializationList = docSpecializationService.getAllDoctorsSpecialization();
        return new ResponseEntity<>(specializationList, HttpStatus.OK);
    }

    @GetMapping("/{spid}")
    public ResponseEntity<DocSpecialization> getDoctorsSpecializationById(@PathVariable Integer spid) {
        return docSpecializationService.getDoctorSpecializationById(spid)
                .map(docSpecialization -> new ResponseEntity<>(docSpecialization, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{spid}")
    public ResponseEntity<String> deleteDoctorsSpecializationById(@PathVariable Integer spid) {
        docSpecializationService.deleteDoctorSpecializationById(spid);
        return new ResponseEntity<>("Doctor specializations removed successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllDoctorsSpecialization() {
        docSpecializationService.deleteAllDoctorsSpecialization();
        return new ResponseEntity<>("All doctors specializations removed successfully", HttpStatus.OK);
    }
}
