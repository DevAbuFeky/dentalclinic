package com.dentaclinic.services.patient;

import com.dentaclinic.model.patient.PatientHistory;
import com.dentaclinic.repository.patient.PatientHistoryQuestionsRepository;
import com.dentaclinic.repository.patient.PatientHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientHistoryServices {

    private final PatientHistoryRepository patientHistoryRepository;
    private final PatientHistoryQuestionsRepository patientHistoryQuestionsRepository;

    public List<PatientHistory> findAll(){
        return patientHistoryRepository.findAll();
    }
    public Optional<PatientHistory> findOne(Long id){
        return patientHistoryRepository.findById(id);
    }

    public void removePatientHistory(Long id) {
        patientHistoryRepository.deleteById(id);
    }


}
