package com.dentaclinic.controller;

import com.dentaclinic.model.Users;
import com.dentaclinic.model.security.Role;
import com.dentaclinic.model.security.UserRole;
import com.dentaclinic.services.userServices.UsersServices;
import com.dentaclinic.utality.SecurityUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@AllArgsConstructor
public class UsersController {

    private final UsersServices usersServices;

    // Registration Code
    @GetMapping("/register")
    public String newUser(){
        return "register";
    }
    @PostMapping(value = "/register")
    public String newUserPost(HttpServletRequest request,
                              @ModelAttribute("email") String userEmail,
                              @ModelAttribute("userName") String userName,
                              @ModelAttribute("firstName") String firstName,
                              @ModelAttribute("lastName") String lastName,
                              @ModelAttribute("password") String password,
                              Model model) throws Exception{

        model.addAttribute("classActiveNewAccount", true);
        model.addAttribute("email",userEmail);
        model.addAttribute("userName", userName);

        if (usersServices.findByUserEmail(userEmail) != null){
            model.addAttribute("emailExists", true);

            return "register";
        }

        if (usersServices.findByUserName(userName) != null){
            model.addAttribute("usernameExists", true);

            return "register";
        }

        Users user = new Users();
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(userEmail);
        user.setPassword(SecurityUtility.passwordEncoder().encode(password));

        Role role = new Role();
        role.setRoleId(2);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user,role));
        usersServices.createUser(user, userRoles);

        return "login";
    }


    //Control users form code
    @GetMapping("/users")
    public String myProfile(Model model){
        List<Users> usersList = usersServices.findAllUsers();
        model.addAttribute("usersList", usersList);
        return "users/usersList";

    }
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") long id, Model model){
        Optional<Users> user = usersServices.findByUserId(id);
        if (user.isPresent()){
            model.addAttribute("user", user.get());
            return "users/updateUser";
        }else {
            return "404";
        }
    }
    @PostMapping("/updateUser")
    public String updateBranchPost(@ModelAttribute("users") Users users,
                                   @ModelAttribute("password") String password,
                                   RedirectAttributes redirectAttributes){
        users.setPassword(SecurityUtility.passwordEncoder().encode(password));
        usersServices.save(users);
        redirectAttributes.addFlashAttribute("message", "The User has been Updated Successfully.");
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, RedirectAttributes redirectAttributes){
        usersServices.removeUserById(id);
        redirectAttributes.addFlashAttribute("message", "Branch has been Deleted Successfully.");
        return "redirect:/users";
    }
    @GetMapping("/createUser")
    public String getCreateNewUser(Model model){
        model.addAttribute("user", new Users());
        return "users/addUser";
    }
    @PostMapping("/createUser")
    public String createNewUser(@ModelAttribute("email") String userEmail,
                                @ModelAttribute("userName") String userName,
                                @ModelAttribute("firstName") String firstName,
                                @ModelAttribute("lastName") String lastName,
                                @ModelAttribute("password") String password,
                                Model model, RedirectAttributes redirectAttributes) throws Exception{

        model.addAttribute("email",userEmail);
        model.addAttribute("userName", userName);

        if (usersServices.findByUserEmail(userEmail) != null){
            model.addAttribute("emailExists", true);

            return "users/addUser";
        }

        if (usersServices.findByUserName(userName) != null){
            model.addAttribute("usernameExists", true);

            return "users/addUser";
        }

        Users user = new Users();
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(userEmail);
        user.setPassword(SecurityUtility.passwordEncoder().encode(password));

        Role role = new Role();
        role.setRoleId(2);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user,role));
        usersServices.createUser(user, userRoles);
        redirectAttributes.addFlashAttribute("message", "User has been saved successfully.");
        return "redirect:/users";
    }

}
