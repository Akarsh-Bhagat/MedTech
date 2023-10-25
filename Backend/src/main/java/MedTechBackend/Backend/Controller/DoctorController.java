package MedTechBackend.Backend.Controller;

import java.text.ParseException;
import java.util.List;

import MedTechBackend.Backend.Model.Doctor;
import MedTechBackend.Backend.Model.Patient;
import MedTechBackend.Backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@CrossOrigin(origins="http://localhost:4200")
@RestController

@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    public DoctorService doctorService;


    //registration
    @PostMapping("/register")
    public ResponseEntity<Doctor> saveAdmin(@Valid @RequestBody Doctor doctor)
    {
        System.out.println("Doctor Registration Succesfull "+doctor);
        return new ResponseEntity<Doctor>(doctorService.saveDoctor(doctor),HttpStatus.CREATED);
    }



    //log in
    @PostMapping("/login")
    public ResponseEntity<Doctor> loginDoctor(@RequestBody Doctor doctor)
    {
        return new ResponseEntity<Doctor>(doctorService.loginDoctor(doctor),HttpStatus.OK);
    }




    @PutMapping("{doctorId}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("doctorId") long doctorId, @RequestBody Doctor doctor)
    {

        return new ResponseEntity<Doctor>(doctorService.updateDoctor(doctor,doctorId),HttpStatus.OK);
    }



    @GetMapping("patient/{doctorId}")
    public List<Patient> getAllPatientsByDoctorId(@PathVariable("doctorId") long doctorId)
    {

        return doctorService.getAllPatientsByDoctorId(doctorId);
    }





    @GetMapping("/doctor")
    public List<Doctor> getAllDoctors()
    {
        return doctorService.getAllDoctors();
    }






    @DeleteMapping("{doctorId}")
    public ResponseEntity<Boolean> deleteDoctorById(@PathVariable long doctorId) {
        doctorService.deleteDoctor(doctorId);
        boolean flag = true;
        return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
    }




    @GetMapping("{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("doctorId") long doctorId){

        return new ResponseEntity<Doctor>(doctorService.getDoctorById(doctorId),HttpStatus.OK);
    }


}