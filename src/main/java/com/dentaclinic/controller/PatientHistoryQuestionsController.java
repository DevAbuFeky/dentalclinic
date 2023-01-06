package com.dentaclinic.controller;

import com.dentaclinic.model.patient.PatientHistoryQuestions;
import com.dentaclinic.repository.patient.PatientHistoryQuestionsRepository;
import com.dentaclinic.services.patient.PatientHistoryQuestionsServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class PatientHistoryQuestionsController {

    private final PatientHistoryQuestionsRepository patientHistoryQuestionsRepository;
    private final PatientHistoryQuestionsServices patientHistoryQuestionsServices;

    @RequestMapping(value = "/addPatientHistoryQuestions", method = RequestMethod.GET)
    public String addPatientHistoryQuestions(Model model,@RequestParam(value = "id") Long id) throws Exception {
        Optional<PatientHistoryQuestions> patientHistoryQuestions = patientHistoryQuestionsRepository.findById(id);
        if (patientHistoryQuestions.isPresent()){
            model.addAttribute("patientHistoryQuestions", patientHistoryQuestions.get());
            return "patientHistoryQuestions/addPatientHistoryQuestions";
        }else {
            throw new Exception("Patient History Question not found");
        }
    }
    @RequestMapping(value = "/addPatientHistoryQuestions", method = RequestMethod.POST)
    public String addPatientHistoryQuestionsPost(@ModelAttribute(name = "patientHistoryQuestions") PatientHistoryQuestions patientHistoryQuestions,
                                        RedirectAttributes redirectAttributes) {
        patientHistoryQuestionsRepository.save(patientHistoryQuestions);
        redirectAttributes.addFlashAttribute("message", "Patient History Questions has been saved successfully.");
        return "redirect:/addPatientHistoryQuestions?id=1";
    }
}
