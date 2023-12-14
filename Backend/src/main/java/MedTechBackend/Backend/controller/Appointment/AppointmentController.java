package MedTechBackend.Backend.controller.Appointment;

import MedTechBackend.Backend.dto.Doctor.DoctorsDTO;
import MedTechBackend.Backend.dto.Patient.PatientDTO;
import MedTechBackend.Backend.entity.Appointment.Appointment;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import MedTechBackend.Backend.entity.Patient.Patient;
import MedTechBackend.Backend.service.Appointment.AppointmentService;
import MedTechBackend.Backend.service.Doctor.DoctorService;
import MedTechBackend.Backend.service.Patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

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
        doctor.setAppointments(doctorsDTO.getAppointments());
        doctor.setTimeSlots(doctorsDTO.getTimeSlots());
        return doctor;
    }

    private Patient convertDTOToEntity(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setId(patientDTO.getId());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setEmail(patientDTO.getEmail());
        patient.setContact(patientDTO.getContact());
        patient.setAddress(patientDTO.getAddress());
        patient.setGender(patientDTO.getGender());
        patient.setAppointment(patientDTO.getAppointment());
        return patient;
    }

    @PostMapping("/request")
    public ResponseEntity<Appointment> requestAppointment(
            @RequestParam Integer patientId,
            @RequestParam Integer doctorId,
            @RequestParam LocalDateTime appointmentTime) {
        // Validate input
        if (patientId == null || doctorId == null || appointmentTime == null) {
            return ResponseEntity.badRequest().build();
        }

        // Fetch patient and doctor by ID
        PatientDTO patient = patientService.getPatientById(patientId);
        DoctorsDTO doctor = doctorService.getDoctorById(doctorId);

        Doctors doctorEntity=convertDTOToEntity(doctor);
        Patient patientEntity=convertDTOToEntity(patient);

        if (patient != null && doctor != null) {
            Appointment appointment = appointmentService.createAppointment(patientEntity, doctorEntity, appointmentTime);
            return ResponseEntity.ok(appointment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/respond")
    public ResponseEntity<Void> respondToAppointment(
            @RequestParam Integer appointmentId,
            @RequestParam boolean accept) {

        if (appointmentId == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Appointment> appointment = appointmentService.getAppointmentById(appointmentId);

        if (appointment != null) {
            appointmentService.respondToAppointment(appointment, accept);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> getAppointmentsByDoctorId(@PathVariable Integer doctorId) {
        List<Appointment> appointments = doctorService.getAppointmentsByDoctorId(doctorId);
        if (appointments != null && !appointments.isEmpty()) {
            return ResponseEntity.ok(appointments);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointments not found for doctor with ID: " + doctorId);
        }
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getAppointmentByDoctorId(@PathVariable Integer patientId) {
        Appointment appointment = patientService.getAppointmentByPatientId(patientId);
        if (appointment != null) {
            return ResponseEntity.ok(appointment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found for patient with ID: " + patientId);
        }
    }




}