package MedTechBackend.Backend.service.Appointment;


import MedTechBackend.Backend.entity.Appointment.Appointment;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import MedTechBackend.Backend.entity.Patient.Patient;
import MedTechBackend.Backend.repository.Appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getPendingAppointmentsForDoctor(Doctors doctor) {
        return appointmentRepository.findByDoctorAndAcceptedIsFalse(doctor);
    }

    public Appointment createAppointment(Patient patient, Doctors doctor, LocalDateTime appointmentTime) {
        // Implement logic to check doctor availability, etc.
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentTime(appointmentTime);
        appointment.setAccepted(false);

        // Save appointment to the database
        return appointmentRepository.save(appointment);
    }

    public void respondToAppointment(Optional<Appointment> optionalAppointment, boolean accept) {
        // Validate input
        if (!optionalAppointment.isPresent()) {
            throw new IllegalArgumentException("Appointment not found.");
        }

        // Get the actual Appointment object from Optional
        Appointment appointment = optionalAppointment.get();

        // Implement logic to handle doctor's response
        appointment.setAccepted(accept);

        // Save the updated appointment to the database
        appointmentRepository.save(appointment);
    }

    public Optional<Appointment> getAppointmentById(Integer appointmentId) {
        // Validate input
        if (appointmentId == null) {
            throw new IllegalArgumentException("Appointment ID cannot be null.");
        }
        // Fetch appointment by ID
        return appointmentRepository.findById(appointmentId);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
