package MedTechBackend.Backend.dto.Patient;

import MedTechBackend.Backend.entity.Patient.MedicalReport;
import MedTechBackend.Backend.entity.Patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClinicDTO {

    private String admissionDate;
    private String dischargeDate;
    private String attendingDoctor;
    private String clinicRoom;
    private String hospital;
    private String department;

    private List<PatientDTO> patients;
    private List<MedicalReport> medicalReports;

}
