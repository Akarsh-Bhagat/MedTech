package MedTechBackend.Backend.service.Doctor;

import MedTechBackend.Backend.dto.Appointment.SearchRequest;
import MedTechBackend.Backend.dto.Doctor.DoctorsDTO;
import MedTechBackend.Backend.entity.Appointment.Appointment;
import MedTechBackend.Backend.entity.Appointment.TimeSlot;
import MedTechBackend.Backend.entity.Doctor.*;
import MedTechBackend.Backend.repository.Appointment.AppointmentRepository;
import MedTechBackend.Backend.repository.Appointment.TimeSlotRepository;
import MedTechBackend.Backend.repository.Doctor.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

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
        List<Appointment> docAppointments= appointmentRepository.findByDoctorAndAcceptedIsFalse(doctor);

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
                .appointments(doctor.getAppointments())
                .timeSlots(doctor.getTimeSlots())
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

//    public List<Doctors> getAvailableDoctorsBySpecialization(DocSpecialization specialization) {
//        return doctorsRepository.findBySpecializationAndAvailableIsTrue(specialization);
//    }

    public void deleteAllDoctors() {
        doctorsRepository.deleteAll();
    }

    public List<Appointment> getAppointmentsByDoctorId(Integer doctorId) {
        Optional<Doctors> optionalDoctor = doctorsRepository.findById(doctorId);
        if (optionalDoctor.isPresent()) {
            Doctors doctor = optionalDoctor.get();
            return appointmentRepository.findByDoctor(doctor);
        } else {
            // Handle the case where the doctor with the specified ID is not found
            throw new EntityNotFoundException("Doctor not found with ID: " + doctorId);
        }
    }

    public List<DoctorsDTO> searchAvailableDoctors(SearchRequest searchRequest) {

        List<Doctors> doctors = doctorsRepository.findBySpecializationName(searchRequest.getSpecializations());
        List<Doctors> availableDoctors = new ArrayList<>();
        for (Doctors doctor : doctors) {
            boolean isAvailable = doctor.getTimeSlots().stream()
                    .anyMatch(timeSlot -> isTimeSlotInRange(timeSlot, searchRequest));
            if (isAvailable) {
                availableDoctors.add(doctor);
            }
        }
        return availableDoctors.stream()
                .map(DoctorsDTO::new)
                .collect(Collectors.toList());
    }
    private boolean isTimeSlotInRange(TimeSlot timeSlot, SearchRequest searchRequest) {
        LocalDateTime startTime = searchRequest.getStartTime();
        LocalDateTime endTime = searchRequest.getEndTime();
        return !timeSlot.getEndTime().isBefore(startTime) && !timeSlot.getStartTime().isAfter(endTime);
    }

}
