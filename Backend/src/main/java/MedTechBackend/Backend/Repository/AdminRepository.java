package MedTechBackend.Backend.Repository;

import MedTechBackend.Backend.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

    public interface AdminRepository extends JpaRepository<Admin,Long> {

        Optional<Admin> findByAdminEmailIdAndAdminPassword(String emailId,String password);
        Optional<Admin> findByAdminEmailId(String adminEmailId);
    }

