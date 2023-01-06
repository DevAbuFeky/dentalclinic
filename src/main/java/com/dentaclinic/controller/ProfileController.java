package com.dentaclinic.controller;

import com.dentaclinic.model.Users;
import com.dentaclinic.services.userServices.UsersServices;
import com.dentaclinic.utality.SecurityUtility;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ProfileController {

    private final UsersServices usersServices;
    private final UserDetailsService userDetailsService;

    @GetMapping("/profile")
    public String myProfile(Model model, Principal principal){
        Users user = usersServices.findByUserName(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("classActiveEdit", true);

        return "profile/users-profile";
    }

    @PostMapping( "/updateUserInfo")
    public String updateUserInfo (@ModelAttribute("user") Users user,
                                  @ModelAttribute("newPassword") String newPassword,
                                  Model model) throws Exception{

        Optional<Users> currentUser = usersServices.findByUserId(user.getId());

        if (currentUser == null){
            throw new Exception("User not found");
        }

        if(currentUser.isPresent()){

//        check email already exists
            if (usersServices.findByUserEmail(user.getEmail()) != null){
                if (!usersServices.findByUserEmail(user.getEmail()).getId().equals(currentUser.get().getId())){
                    model.addAttribute("emailExists", true);
                    return "profile/users-profile";
                }
            }

            //        check username already exists
            if (usersServices.findByUserName(user.getUserName()) != null){
                if (usersServices.findByUserName(user.getUserName()).getId() != currentUser.get().getId()){
                    model.addAttribute("usernameExists", true);
                    return "profile/users-profile";
                }
            }

            // update password
            if (newPassword != null && !newPassword.isEmpty()){
                BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
                String dbPassword = currentUser.get().getPassword();
                if (passwordEncoder.matches(user.getPassword(), dbPassword)){
                    currentUser.get().setPassword(passwordEncoder.encode(newPassword));
                }else {
                    model.addAttribute("incorrectPassword", true);

                    return "profile/users-profile";
                }
            }
        }

        currentUser.get().setFirstName(user.getFirstName());
        currentUser.get().setLastName(user.getLastName());
        currentUser.get().setUserName(user.getUserName());
        currentUser.get().setEmail(user.getEmail());
        currentUser.get().setPhone(user.getPhone());
        currentUser.get().setAddress(user.getAddress());

        usersServices.save(currentUser.get());

        model.addAttribute("updateSuccess", true);
        model.addAttribute("user", currentUser.get());
        model.addAttribute("classActiveEdit",true);

        UserDetails userDetails = userDetailsService.loadUserByUsername(currentUser.get().getUserName());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "profile/users-profile";
    }
}


