package MedTechBackend.Backend.entity.Appointment;

import MedTechBackend.Backend.entity.Doctor.DocSpecialization;
import MedTechBackend.Backend.entity.Doctor.Doctors;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "time_slot")
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonBackReference(value = "doctor-timeSlot")
    private Doctors doctor;

    @Column(name = "start_time")
    @Future(message = "Timestamp must be in the future")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    @Future(message = "Timestamp must be in the future")
    private LocalDateTime endTime;

    @Column(name="is_available")
    @NotNull(message = "Please enter boolean value")
    private boolean isAvailable;
}