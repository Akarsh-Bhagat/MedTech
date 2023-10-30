//package MedTechBackend.Backend.doctor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class DoctorService {
//
//    private final DoctorRepository doctorRepository;
//
//    @Autowired
//    public DoctorService(DoctorRepository doctorRepository) {
//        this.doctorRepository = doctorRepository;
//    }
//
//    public Doctor createDoctor(Doctor doctor) {
//        return doctorRepository.save(doctor);
//    }
//
//    public Doctor getDoctorById(Integer id) {
//        return doctorRepository.findById(id).orElse(null);
//    }
//
//    public List<Doctor> getAllDoctors() {
//        return doctorRepository.findAll();
//    }
//
//    public Doctor updateDoctor(Doctor doctor) {
//        if (doctorRepository.existsById(doctor.getId())) {
//            return doctorRepository.save(doctor);
//        }
//        return null;
//    }
//
//    public boolean deleteDoctor(Integer id) {
//        if (doctorRepository.existsById(id)) {
//            doctorRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
//
//    public Optional<Doctor> getDoctorByEmail(String email) {
//        return doctorRepository.findByUserEmail(email);
//    }
//}
