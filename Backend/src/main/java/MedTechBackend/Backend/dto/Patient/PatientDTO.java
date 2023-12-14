package MedTechBackend.Backend.dto.Patient;

import MedTechBackend.Backend.entity.Appointment.Appointment;
import MedTechBackend.Backend.entity.Doctor.*;
import MedTechBackend.Backend.entity.Patient.Clinic;
import MedTechBackend.Backend.entity.Patient.MedicalHistory;
import MedTechBackend.Backend.entity.Patient.MedicalReport;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
public class PatientDTO {
    private Integer id;

    @NotEmpty(message = "Firstname is required")
    private String firstName;
    @NotEmpty(message = "Lastname is required")
    private String lastName;
    @NotEmpty(message = "Email is required")
    private String email;
    @NotEmpty(message = "Address is required")
    private String address;
    @NotEmpty(message = "Contact is required")
    private String contact;
    private String gender;
    private LocalDate dateOfBirth;

    private MedicalHistory medicalHistory;
    private List<MedicalReport> medicalReports;
    private List<Clinic> clinics;
    private Appointment appointment;

}
