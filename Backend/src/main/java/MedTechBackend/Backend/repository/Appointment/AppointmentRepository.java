package MedTechBackend.Backend.repository.Appointment;

import MedTechBackend.Backend.entity.Appointment.Appointment;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import MedTechBackend.Backend.entity.Patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByDoctorAndAcceptedIsFalse(Doctors doctor);
    List<Appointment> findByDoctor(Doctors doctor);
    Appointment findByPatient(Patient patient);

}