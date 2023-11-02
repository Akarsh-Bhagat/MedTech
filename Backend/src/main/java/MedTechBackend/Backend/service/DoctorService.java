package MedTechBackend.Backend.service;

import MedTechBackend.Backend.dto.DocExperienceDTO;
import MedTechBackend.Backend.dto.DoctorsDTO;
import MedTechBackend.Backend.entity.DocExperience;
import MedTechBackend.Backend.entity.Doctors;
import MedTechBackend.Backend.repository.DocExperienceRepository;
import MedTechBackend.Backend.repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private DocExperienceRepository docExperienceRepository;

    public void createDoctor(Doctors doctors) {
        doctorsRepository.save(doctors);
    }

    public List<Doctors> getAllDoctors() {
        return doctorsRepository.findAll();
    }

    public DoctorsDTO getDoctorById(Integer docid) {
        Optional<Doctors> doctorsOptional = doctorsRepository.findById(docid);
        if (doctorsOptional.isEmpty()) {
            return null;
        }

        Doctors doctors = doctorsOptional.get();
        Optional<DocExperience> docExperiences = docExperienceRepository.findByDoctor(doctors);
        List<DocExperienceDTO> docExperienceDTOs= docExperiences.stream()
                .map(DocExperienceDTO::new) // Assuming DoctorExperienceDTO has a suitable constructor
                .collect(Collectors.toList());

        return DoctorsDTO.builder()
                .id(doctors.getId())
                .firstName(doctors.getFirstName())
                .lastName(doctors.getLastName())
                .dateOfBirth(doctors.getDateOfBirth())
                .email(doctors.getEmail())
                .experiences(docExperienceDTOs)
                .build();
    }

    public void updateDoctor(Integer docid, Doctors updatedDoctor) {
        doctorsRepository.findById(docid).ifPresent(existingDoctor -> {
            existingDoctor.setFirstName(updatedDoctor.getFirstName());
            existingDoctor.setLastName(updatedDoctor.getLastName());
            existingDoctor.setEmail(updatedDoctor.getEmail());
            existingDoctor.setAddress(updatedDoctor.getAddress());
            existingDoctor.setDateOfBirth(updatedDoctor.getDateOfBirth());
            existingDoctor.setSpecialisation(updatedDoctor.getSpecialisation());
            doctorsRepository.save(existingDoctor);
        });
    }

    public boolean deleteDoctorById(Integer docid) {
        doctorsRepository.deleteById(docid);
        return false;
    }

    public void deleteAllDoctors() {
        doctorsRepository.deleteAll();
    }
}
