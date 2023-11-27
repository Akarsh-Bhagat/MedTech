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
public class DocAwardDTO {
    private Integer id;
    private String title;
    private Year recYear;
    private List<DoctorsDTO> doctors;
}
