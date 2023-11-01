package MedTechBackend.Backend.dto;

import MedTechBackend.Backend.entity.DocExperience;
import MedTechBackend.Backend.entity.Doctors;
import jakarta.persistence.*;
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
public class DoctorDetails {
    private int doctorId;

    private String firstname;

    private String lastname;

    private String email;

    private String address;

    private LocalDate dob;

    private String specialisation;

    private List<DocExperience> docExperience;

    private int expId;

    private String description;

    private Doctors doctors;

}
