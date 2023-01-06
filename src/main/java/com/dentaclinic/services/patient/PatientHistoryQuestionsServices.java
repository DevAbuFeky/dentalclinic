package com.dentaclinic.services.patient;

import com.dentaclinic.model.patient.PatientHistoryQuestions;
import com.dentaclinic.repository.patient.PatientHistoryQuestionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientHistoryQuestionsServices {

    private final PatientHistoryQuestionsRepository patientHistoryQuestionsRepository;

    public List<PatientHistoryQuestions> findAll(){
        return patientHistoryQuestionsRepository.findAll();
    }

    public Optional<PatientHistoryQuestions> findPatientHistoryQuestions(Long id){
        return patientHistoryQuestionsRepository.findById(id);
    }
}
