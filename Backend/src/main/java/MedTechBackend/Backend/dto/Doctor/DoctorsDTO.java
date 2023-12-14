package MedTechBackend.Backend.dto.Doctor;

import MedTechBackend.Backend.entity.Appointment.Appointment;
import MedTechBackend.Backend.entity.Appointment.TimeSlot;
import MedTechBackend.Backend.entity.Doctor.*;
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
public class DoctorsDTO {

    private Integer id;
    @NotEmpty(message = "Firstname is required")
    private String firstName;
    @NotEmpty(message = "Lastname is required")
    private String lastName;
    @NotEmpty(message = "Email is required")
    @Email
    private String email;
    @NotEmpty(message = "Address is required")
    private String address;
    private LocalDate dateOfBirth;
    private String specialisation;

    private List<DocExperience> experiences;
    private List<DocAward> awards;
    private List<DocEducation> education;
    private List<DocMembership> memberships;
    private List<DocServicing> servicings;
    private List<DocSpecialization> specializations;
    private List<DocHandle> handles;
    private List<DocRegistration> registrations;
    private List<Appointment> appointments;
    private List<TimeSlot> timeSlots;

    public DoctorsDTO(Doctors doctors) {
    }
}
