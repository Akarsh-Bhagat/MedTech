package MedTechBackend.Backend.service.Patient;

import MedTechBackend.Backend.entity.Patient.Clinic;
import MedTechBackend.Backend.repository.Patient.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }


    public void createClinic(Clinic clinic) {
        clinicRepository.save(clinic);
    }

    public List<Clinic> getAllClinic() {
        return new ArrayList<>(clinicRepository.findAll());
    }

    public Optional<Clinic> getClinicById(Integer id) {
        return clinicRepository.findById(id);
    }

    public Clinic getClinicByHospital(String hospital) {
        return clinicRepository.findByHospital(hospital);
    }

    public void deleteClinicById(Integer id) {
        clinicRepository.deleteById(id);
    }

    public void deleteAllClinic() {
        clinicRepository.deleteAll();
    }
}
