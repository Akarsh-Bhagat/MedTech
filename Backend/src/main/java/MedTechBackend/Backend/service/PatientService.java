package MedTechBackend.Backend.service;


import MedTechBackend.Backend.Model.Patient;

public interface PatientService {

    Patient savePatient(Patient patient);

    Patient loginPatient(Patient patient);

    Patient updatePatient(Patient patient, long patientId);

    Patient getPatientById(long patientId);



}
