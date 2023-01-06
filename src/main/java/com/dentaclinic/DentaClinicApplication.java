package com.dentaclinic;

import com.dentaclinic.model.Users;
import com.dentaclinic.model.security.Role;
import com.dentaclinic.model.security.UserRole;
import com.dentaclinic.services.userServices.UsersServices;
import com.dentaclinic.utality.SecurityUtility;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DentaClinicApplication implements CommandLineRunner {

    private final UsersServices usersServices;
    public DentaClinicApplication(UsersServices usersServices) {
        this.usersServices = usersServices;
    }

    public static void main(String[] args) {
        SpringApplication.run(DentaClinicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Users user = new Users();

        user.setUserName("admin");
        user.setFirstName("Admin");
        user.setLastName("Portal");
        user.setPassword(SecurityUtility.passwordEncoder().encode("password"));
        user.setEmail("admin@gmail.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setName("ROLE_ADMIN");
        userRoles.add(new UserRole(user, role1));

        usersServices.createUser(user, userRoles);
    }
}
