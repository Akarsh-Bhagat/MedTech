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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "doctors", cascade = CascadeType.ALL)
//    private List<DocExperience> docExperience;
    private Set<DocExperience> docExperience= new LinkedHashSet<>();

    @Override
    public String toString() {
        return "Doctors{" +
                "doctorId=" + doctorId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", specialisation='" + specialisation + '\'' +
                ", docExperience=" + docExperience +
                '}';
    }
}
