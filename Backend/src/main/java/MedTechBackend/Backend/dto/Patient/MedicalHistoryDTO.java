package MedTechBackend.Backend.dto.Patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryDTO {
    private List<String> pastConditions;
    private List<String> allergies;
    private List<String> surgeries;
    private List<String> medications;
    private String familyHistory;
    private String bloodGroup;

    private PatientDTO patientDTO;
}
