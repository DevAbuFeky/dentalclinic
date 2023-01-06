package com.dentaclinic.services.userServices;

import com.dentaclinic.model.Users;
import com.dentaclinic.model.security.PasswordResetToken;
import com.dentaclinic.model.security.UserRole;
import com.dentaclinic.repository.userRepo.PasswordResetTokenRepository;
import com.dentaclinic.repository.userRepo.RoleRepository;
import com.dentaclinic.repository.userRepo.UsersRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UsersServices {

    private final UsersRepo usersRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final PasswordResetTokenRepository passwordResetTokenRepository;


    private static final Logger LOG = LoggerFactory.getLogger(UsersServices.class);

    public UsersServices(UsersRepo usersRepo, PasswordEncoder passwordEncoder, RoleRepository roleRepository, PasswordResetTokenRepository passwordResetTokenRepository) {
        this.usersRepo = usersRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    public List<Users> findAllUsers(){
        return usersRepo.findAll();
    }

    public Optional<Users> findByUserId(Long id){
        return usersRepo.findById(id);
    }

    public Users findByUserName(String userName){
        return usersRepo.findByUserName(userName);
    }
    public Users findByUserEmail(String userEmail){
        return usersRepo.findByEmail(userEmail);
    }

    public void removeUserById(Long id){
        usersRepo.deleteById(id);
    }

    public Users save(Users user){
        return usersRepo.save(user);
    }

    public PasswordResetToken getPasswordResetToken(final String token){
        return passwordResetTokenRepository.findByToken(token);
    }

    public void createPasswordResetTokenForUser(final Users user, final String token){
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }

    //to create local user using commandLineRunner
    @Transactional
    public Users createUser(Users user, Set<UserRole> userRoles) throws Exception{
        Users localUser = usersRepo.findByUserName(user.getUserName());

        if(localUser != null) {
            LOG.info("user {} already exists. Nothing will be done", user.getUserName());
        }else {
            for (UserRole ur : userRoles){
                roleRepository.save(ur.getRole());
            }

            user.getUserRole().addAll(userRoles);

            localUser = usersRepo.save(user);
        }

        return localUser;
    }

}
