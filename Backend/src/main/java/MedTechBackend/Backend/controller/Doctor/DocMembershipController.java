package MedTechBackend.Backend.controller.Doctor;

import MedTechBackend.Backend.dto.Doctor.DoctorsDTO;
import MedTechBackend.Backend.entity.Doctor.DocMembership;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import MedTechBackend.Backend.service.Doctor.DocMembershipService;
import MedTechBackend.Backend.service.Doctor.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors/memberships")
public class DocMembershipController {

    private final DocMembershipService docMembershipService;

    private final DoctorService doctorService;

    public DocMembershipController(DocMembershipService docMembershipService, DoctorService doctorService) {
        this.docMembershipService = docMembershipService;
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
    public ResponseEntity<String> createNewDoctorMembership(@PathVariable Integer docid, @RequestBody DocMembership docMembership) {
        DoctorsDTO doctorDetails = doctorService.getDoctorById(docid);
        Doctors doctorEntity=convertDTOToEntity(doctorDetails);
        if (doctorEntity != null) {
            docMembership.setDoctor(doctorEntity);
            docMembershipService.createDoctorMembership(docMembership);
            return new ResponseEntity<>("Doctor membership added in the database", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DocMembership>> getAllDoctorsMembership() {
        List<DocMembership> membershipList = docMembershipService.getAllDoctorsMembership();
        return new ResponseEntity<>(membershipList, HttpStatus.OK);
    }

    @GetMapping("/{memid}")
    public ResponseEntity<DocMembership> getDoctorsMembershipById(@PathVariable Integer memid) {
        return docMembershipService.getDoctorMembershipById(memid)
                .map(docMembership -> new ResponseEntity<>(docMembership, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{memid}")
    public ResponseEntity<String> deleteDoctorsMembershipById(@PathVariable Integer memid) {
        docMembershipService.deleteDoctorMembershipById(memid);
        return new ResponseEntity<>("Doctor memberships removed successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllDoctorsMembership() {
        docMembershipService.deleteAllDoctorsMembership();
        return new ResponseEntity<>("All doctors memberships removed successfully", HttpStatus.OK);
    }
}
