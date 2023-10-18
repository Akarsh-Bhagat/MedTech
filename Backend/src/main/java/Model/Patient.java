package Model;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name= "patient_table")
@SequenceGenerator(name="patient",sequenceName="patient_gene",initialValue=3000)

public class Patient {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="patient")
    @Column(name="patient_id")
    private long patientId;

    @Column(name="first_name")
    @NotEmpty
    @Size(min=4,message="firstName should contain atleast 4 letters in it")
    public String firstName;

    @Column(name="last_name")
    @NotEmpty
    @Size(min=4,message="lastName should contain atleast 4 letters in it")
    public String lastName;

    @Column(name="email_id",unique=true)
    @NotEmpty
    @Email(message="Email  is not valid!")
    public String patientEmailId;

    @Column(name="passWord")
    @NotEmpty
    @Size(min=8, message="Password length must be 8 and contain uppercase,lowercase,digits")
    @Pattern(regexp="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")
    public String patientPassword;

    @Column(name="age")
    @NotNull
    public int age;

    @Column(name="gender")
    @NotEmpty
    @Size(min=4,message="please enter gender as male , female , not disclosed")
    public String gender;

    @Column(name="contact_number")
    @NotEmpty
    @Size(min=5,message="please enter a valid contact number")
    public String contactNumber;

    @Column(name="address")
    @NotEmpty
    @Size(min=3 , message="address must contain atleast 3 characters")
    public String address;

    @Column(name="blood_group")
    @NotEmpty
    @Size(min=2 , message="blood group must contain atleast 2 characters")
    public String bloodGroup;

    @Column(name="medical_background")
    @NotEmpty
    @Size(min=3,message="specialization should have atleast 3 letters")
    public String medicalBackground;

    @Column(name="admit_date")
    public Date admitDate;


//
//    @Column(name="doctor_id")
//    public long doctorId;
//
//    @Column(name="payment_status")
//
//    private String paymentStatus;

}
