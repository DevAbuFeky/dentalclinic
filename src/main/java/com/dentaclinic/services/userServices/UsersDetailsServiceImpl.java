package com.dentaclinic.services.userServices;

import com.dentaclinic.model.Users;
import com.dentaclinic.repository.userRepo.UsersRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailsServiceImpl implements UserDetailsService {

    private final UsersRepo usersRepo;

    public UsersDetailsServiceImpl(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Users users = usersRepo.findByUserName(userName);
        System.out.println(users);

        if (users == null){
            System.out.println("Exception Error");
            throw new UsernameNotFoundException(userName + "not found");
        }
        return new UserDetailsImpl(users);
    }
}
