package MedTechBackend.Backend.repository.Appointment;

import MedTechBackend.Backend.entity.Appointment.TimeSlot;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {

    List<TimeSlot> findByDoctorAndStartTimeAfterAndEndTimeBefore(Doctors doctor, LocalDateTime start, LocalDateTime end);

}