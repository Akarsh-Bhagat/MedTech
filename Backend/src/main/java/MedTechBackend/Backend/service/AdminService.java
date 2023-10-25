package MedTechBackend.Backend.service;



import MedTechBackend.Backend.Model.Admin;

import java.util.List;



public interface AdminService {

    Admin saveAdmin(Admin admin);

    Admin loginAdmin(Admin admin);

    List<Admin> getAllAdmins();

    Admin getAdminById(long adminId);

    void deleteAdmin(long adminId);

    Admin updateAdmin(Admin admin, long adminId);


}