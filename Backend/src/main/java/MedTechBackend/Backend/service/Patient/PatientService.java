package MedTechBackend.Backend.service.Patient;

import MedTechBackend.Backend.dto.Patient.PatientDTO;
import MedTechBackend.Backend.entity.Patient.*;
import MedTechBackend.Backend.repository.Patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;


    public void createPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    public PatientDTO getPatientById(Integer id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) {
            return null;
        }
        System.out.println(patientOptional);

        Patient patient = patientOptional.get();
        MedicalHistory medicalHistory=medicalHistoryRepository.findByPatient(patient);

        return PatientDTO.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .address((patient.getAddress()))
                .dateOfBirth(patient.getDateOfBirth())
                .contact((patient.getContact()))
                .email(patient.getEmail())
                .gender(patient.getGender())
                .medicalHistory(patient.getMedicalHistory())
                .build();
    }

    public void updatePatient(Integer id, Patient updatedPatient) {
        patientRepository.findById(id).ifPresent(existingPatient -> {
            existingPatient.setFirstName(updatedPatient.getFirstName());
            existingPatient.setLastName(updatedPatient.getLastName());
            existingPatient.setEmail(updatedPatient.getEmail());
            existingPatient.setAddress(updatedPatient.getAddress());
            existingPatient.setDateOfBirth(updatedPatient.getDateOfBirth());
            existingPatient.setContact(updatedPatient.getContact());
            existingPatient.setGender(updatedPatient.getGender());
            patientRepository.save(existingPatient);
        });
    }

    public boolean deletePatientById(Integer id) {
        patientRepository.deleteById(id);
        return true;
    }

    public void deleteAllPatient() {
        patientRepository.deleteAll();
    }
}
