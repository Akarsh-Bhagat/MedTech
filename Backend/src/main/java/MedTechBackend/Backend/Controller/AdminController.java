package MedTechBackend.Backend.Controller;

import java.util.List;

import MedTechBackend.Backend.Model.Admin;
import MedTechBackend.Backend.Model.Doctor;
import MedTechBackend.Backend.service.AdminService;
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

@RequestMapping("/api/admin")

public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private DoctorService doctorService;


    @PostMapping("/register")
    public ResponseEntity<Admin> saveAdmin(@Valid @RequestBody Admin admin){
        System.out.println("Admin Registration Succesfull "+admin);
        return new ResponseEntity<Admin>(adminService.saveAdmin(admin),HttpStatus.CREATED);
    }



    @PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin(@RequestBody Admin admin)
    {
        return new ResponseEntity<Admin>(adminService.loginAdmin(admin),HttpStatus.OK);
    }




    //get all admin details
    @GetMapping
    public List<Admin> getAllAdmins()
    {
        return adminService.getAllAdmins();
    }




    //get admin by id
    @GetMapping("{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("adminId") long adminId)
    {

        return new ResponseEntity<Admin>(adminService.getAdminById(adminId),HttpStatus.OK);
    }




    //updating admin details
    @PutMapping("{adminId}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("adminId") long adminId, @RequestBody Admin admin)
    {

        return new ResponseEntity<Admin>(adminService.updateAdmin(admin,adminId),HttpStatus.OK);
    }





    //delete by id
    @DeleteMapping("{adminId}")
    public ResponseEntity<Boolean> deleteAdmin(@PathVariable("adminId") long adminId)
    {
        adminService.deleteAdmin(adminId);
        boolean flag = true;
        return new ResponseEntity<Boolean>(flag, HttpStatus.OK);

    }






    @PostMapping("/register/doctor")
    public ResponseEntity<Doctor> saveAdmin(@Valid @RequestBody Doctor doctor)
    {
        System.out.println("Doctor Registration Succesfull "+doctor);
        return new ResponseEntity<Doctor>(doctorService.saveDoctor(doctor),HttpStatus.CREATED);
    }




    //get all doctor details
    @GetMapping("/doctor")
    public List<Doctor> getAllDoctors()
    {
        return doctorService.getAllDoctors();
    }





    //get doctor by id
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("doctorId") long doctorId)
    {

        return new ResponseEntity<Doctor>(doctorService.getDoctorById(doctorId),HttpStatus.OK);
    }





    //updating doctor details
    @PutMapping("/doctor/{doctorId}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("doctorId") long doctorId, @RequestBody Doctor doctor)
    {

        return new ResponseEntity<Doctor>(doctorService.updateDoctor(doctor,doctorId),HttpStatus.OK);
    }





    //delete by id
    @DeleteMapping("/doctor/{doctorId}")
    public ResponseEntity<Boolean> deleteDoctor(@PathVariable("doctorId") long doctorId)
    {
        doctorService.deleteDoctor(doctorId);
        boolean flag = true;
        return new ResponseEntity<Boolean>(flag, HttpStatus.OK);

    }
}