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
public class DocExperienceDTO {
    private Integer id;
    private String hospital;
    private String city;
    private Year startYear;
    private Year endYear;
    private String description;
    private List<DoctorsDTO> doctors;
}
