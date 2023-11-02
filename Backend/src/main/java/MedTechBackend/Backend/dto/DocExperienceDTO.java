package MedTechBackend.Backend.dto;

import MedTechBackend.Backend.entity.DocExperience;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocExperienceDTO {
    private Integer id;
    private String description;

    public DocExperienceDTO(DocExperience experiences) {
        this.id = experiences.getId();
        this.description = experiences.getDescription();
    }

    // Getters, setters, and additional methods
}
