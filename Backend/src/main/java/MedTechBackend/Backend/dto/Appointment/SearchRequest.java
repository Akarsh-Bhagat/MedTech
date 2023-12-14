package MedTechBackend.Backend.dto.Appointment;

import MedTechBackend.Backend.entity.Appointment.TimeSlot;
import MedTechBackend.Backend.entity.Doctor.DocSpecialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    private List<String> specializations;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}