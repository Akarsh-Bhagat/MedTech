package MedTechBackend.Backend.entity;

//import MedTechBackend.Backend.entity.DocExperience;
import MedTechBackend.Backend.security.token.Token;
import MedTechBackend.Backend.user.Role;
import MedTechBackend.Backend.user.User;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.config.annotation.web.oauth2.login.OAuth2LoginSecurityMarker;

import java.security.Provider;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int doctorId;

    @Column(name = "doc_firstname")
    private String firstname;

    @Column(name = "doc_lastname")
    private String lastname;

    @Column(name = "doc_email")
    private String email;

    @Column(name = "doc_address")
    private String address;

    @Column(name = "doc_dob")
    private LocalDate dob;

    @Column(name = "doc_spec")
    private String specialisation;

//    @OneToMany(mappedBy = "doctors", cascade = CascadeType.ALL)
//    private List<Service> service;

    @ManyToOne
    @JoinColumn(name = "experience_id")
    private DocExperience docExperience;



}
