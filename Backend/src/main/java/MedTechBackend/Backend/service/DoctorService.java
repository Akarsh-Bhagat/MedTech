package MedTechBackend.Backend.service;//package MedTechBackend.Backend.doctor;

import MedTechBackend.Backend.dto.DoctorDetails;
import MedTechBackend.Backend.entity.DocExperience;
import MedTechBackend.Backend.entity.Doctors;
import MedTechBackend.Backend.repository.DocExperienceRepository;
import MedTechBackend.Backend.repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorsRepository doctorsRepository;
    @Autowired
    DocExperienceRepository docExperienceRepository;

    public void createDoctor(Doctors doctors) {
        doctorsRepository.save(doctors);
    }

    public List<Doctors> getAllDoctors() {
        List<Doctors> docList = new ArrayList<>();
        doctorsRepository.findAll().forEach(docList::add);
        return docList;
    }

    public DoctorDetails getDoctorById(Integer docid) {
        Doctors doctors = doctorsRepository.findById(docid).orElse(null);
        DocExperience docExperience = docExperienceRepository.findByDoctors(doctors).orElse(null);
        List<DocExperience> docExperiences = new ArrayList<>();
        docExperiences.add(docExperience);
        System.out.println(doctors);
        System.out.println(docExperience);
        DoctorDetails doctorDetails = DoctorDetails.builder()
                .doctorId(doctors.getDoctorId())
                .firstname(doctors.getFirstname())
                .lastname(doctors.getLastname())
                .dob(doctors.getDob())
                .email(doctors.getEmail())
                .docExperience(docExperiences)
                .build();

        return doctorDetails;
    }

    public void updateDoctor(Integer docid, Doctors updatedDoctor) {
        Optional<Doctors> doc = doctorsRepository.findById(docid);
        if (doc.isPresent()) {
            Doctors existingDoctor = doc.get();
            // Update the existing doctor with the provided information
            existingDoctor.setFirstname(updatedDoctor.getFirstname());
            existingDoctor.setLastname(updatedDoctor.getLastname());
            existingDoctor.setEmail(updatedDoctor.getEmail());
            existingDoctor.setAddress(updatedDoctor.getAddress());
            existingDoctor.setDob(updatedDoctor.getDob());
            existingDoctor.setSpecialisation(updatedDoctor.getSpecialisation());
            doctorsRepository.save(existingDoctor);
        }
    }

    public void deleteDoctorById(Integer docid) {
        doctorsRepository.deleteById(docid);
    }

    public void deleteAllDoctors() {
        doctorsRepository.deleteAll();
    }
}

