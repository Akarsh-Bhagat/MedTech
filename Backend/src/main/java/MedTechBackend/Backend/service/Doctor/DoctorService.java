package MedTechBackend.Backend.service.Doctor;

import MedTechBackend.Backend.dto.Doctor.DoctorsDTO;
import MedTechBackend.Backend.entity.Doctor.*;
import MedTechBackend.Backend.repository.Doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private DocExperienceRepository docExperienceRepository;

    @Autowired
    private DocAwardRepository docAwardRepository;

    @Autowired
    private DocEducationRepository docEducationRepository;

    @Autowired
    private DocServicingRepository docServicingRepository;

    @Autowired
    private DocMembershipRepository docMembershipRepository;

    @Autowired
    private DocSpecializationRepository docSpecializationRepository;

    @Autowired
    private DocHandleRepository docHandleRepository;

    @Autowired
    private DocRegistrationRepository docRegistrationRepository;

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
        System.out.println(doctorsOptional);

        Doctors doctor = doctorsOptional.get();
        List<DocExperience> docExperiences = docExperienceRepository.findByDoctor(doctor);
        List<DocAward> docAwards = docAwardRepository.findByDoctor(doctor);
        List<DocEducation> docEducation = docEducationRepository.findByDoctor(doctor);
        List<DocServicing> docServicings = docServicingRepository.findByDoctor(doctor);
        List<DocMembership> docMemberships = docMembershipRepository.findByDoctor(doctor);
        List<DocSpecialization> docSpecializations = docSpecializationRepository.findByDoctor(doctor);
        List<DocHandle> docHandles= docHandleRepository.findByDoctor(doctor);
        List<DocRegistration> docRegistrations= docRegistrationRepository.findByDoctor(doctor);

        return DoctorsDTO.builder()
                .id(doctor.getId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .address((doctor.getAddress()))
                .dateOfBirth(doctor.getDateOfBirth())
                .specialisation((doctor.getSpecialisation()))
                .email(doctor.getEmail())
                .experiences(doctor.getExperiences())
                .awards(doctor.getAwards())
                .education(doctor.getEducation())
                .memberships(doctor.getMemberships())
                .servicings(doctor.getServicings())
                .specializations(doctor.getSpecializations())
                .handles(doctor.getHandles())
                .registrations(doctor.getRegistrations())
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
        return true;
    }

    public void deleteAllDoctors() {
        doctorsRepository.deleteAll();
    }
}
