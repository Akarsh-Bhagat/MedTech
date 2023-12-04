package MedTechBackend.Backend.dto.Doctor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocRegistrationDTO {
    private Integer id;
    private Integer regId;
    private String council;
    private Year validFrom;
    private List<DoctorsDTO> doctors;
}
