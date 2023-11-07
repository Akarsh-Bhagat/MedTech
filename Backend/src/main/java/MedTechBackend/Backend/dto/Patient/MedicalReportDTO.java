package MedTechBackend.Backend.dto.Patient;

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
public class MedicalReportDTO {
    private LocalDate dateOfVisit;
    private LocalDate reportDate;
    private String diagnosis;
    private String symptoms;
    private String treatment;
    private String prescription;
    private String hospital;

    private List<PatientDTO> patient;
    private List<ClinicDTO> clinic;
}
