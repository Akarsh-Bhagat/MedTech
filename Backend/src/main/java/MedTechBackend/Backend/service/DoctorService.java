package MedTechBackend.Backend.service;//package MedTechBackend.Backend.doctor;

import MedTechBackend.Backend.entity.Doctors;
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

    public void createDoctor(Doctors doctors) {
        doctorsRepository.save(doctors);
    }

    public List<Doctors> getAllDoctors() {
        List<Doctors> docList = new ArrayList<>();
        doctorsRepository.findAll().forEach(docList::add);
        return docList;
    }

    public Optional<Doctors> getDoctorById(Integer docid) {
        return doctorsRepository.findById(docid);
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

