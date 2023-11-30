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
public class DocMembershipDTO {
    private Integer id;
    private String history;
    private Year startYear;
    private Year endYear;
    private List<DoctorsDTO> doctors;
}
