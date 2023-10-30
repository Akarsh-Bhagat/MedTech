package MedTechBackend.Backend.doctor;

import MedTechBackend.Backend.security.token.Token;
import MedTechBackend.Backend.user.Role;
import MedTechBackend.Backend.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.config.annotation.web.oauth2.login.OAuth2LoginSecurityMarker;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="doctors")
public class Doctors {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;

    @Column(name = "doc_firstname")
    private String firstname;

    @Column(name = "doc_lastname")
    private String lastname;

    @Column(name = "doc_email")
    private String email;

    @Column(name = "doc_adress")
    private String address;

    @Column(name = "doc_dob")
    private LocalDate dob;

    @Column(name = "doc_spec")
    private String specialisation;

}
