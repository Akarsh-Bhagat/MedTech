package MedTechBackend.Backend.dto.Doctor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocExperienceDTO {
    private Integer id;
    private String description;
    private List<DoctorsDTO> doctors;
}
