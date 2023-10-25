package MedTechBackend.Backend.service;

import MedTechBackend.Backend.Model.Doctor;
import MedTechBackend.Backend.Model.Patient;

import java.util.List;



public interface DoctorService {

    Doctor saveDoctor(Doctor doctor);

    Doctor loginDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();

    Doctor getDoctorById(long doctorId);

    void deleteDoctor(long doctorId);

    Doctor updateDoctor(Doctor doctor, long doctorId);

    List<Patient> getAllPatientsByDoctorId(long doctorId);




}