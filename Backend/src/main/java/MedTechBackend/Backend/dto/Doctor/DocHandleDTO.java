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
public class DocHandleDTO {
    private Integer id;
    private String handle;
    private String link;
    private List<DoctorsDTO> doctors;
}
