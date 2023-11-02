package MedTechBackend.Backend.dto;

import MedTechBackend.Backend.entity.Doctors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorsDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private LocalDate dateOfBirth;
    private String specialisation;
    private List<DocExperienceDTO> experiences;

    private Doctors doctor;

    // Getters, setters, and additional methods
}
