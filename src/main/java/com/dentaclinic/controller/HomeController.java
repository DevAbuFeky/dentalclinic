package com.dentaclinic.controller;

import com.dentaclinic.model.Users;
import com.dentaclinic.model.patient.PatientHistory;
import com.dentaclinic.services.patient.PatientHistoryServices;
import com.dentaclinic.services.userServices.UsersServices;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private final UsersServices usersServices;
    private final PatientHistoryServices patientHistoryServices;

    @GetMapping("/home")
    public String home(Model model) {
        List<Users> users = usersServices.findAllUsers();
        List<PatientHistory> patientHistory = patientHistoryServices.findAll();
        model.addAttribute("users", users);
        model.addAttribute("patientHistory", patientHistory);
        return "index";
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
