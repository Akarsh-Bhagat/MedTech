package MedTechBackend.Backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctors")
public class Doctors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "doc_firstname")
    private String firstName;

    @Column(name = "doc_lastname")
    private String lastName;

    @Column(name = "doc_email")
    private String email;

    @Column(name = "doc_address")
    private String address;

    @Column(name = "doc_dob")
    private LocalDate dateOfBirth;

    @Column(name = "doc_spec")
    private String specialisation;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocExperience> experiences;

    // Getters, setters, and additional methods

    @Override
    public String toString() {
        return "Doctors{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", specialisation='" + specialisation + '\'' +
                '}';
    }
}
