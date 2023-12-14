package MedTechBackend.Backend.dto.Appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlotDTO {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isAvailable;
}
