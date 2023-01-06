package com.dentaclinic.services.patient;

import com.dentaclinic.model.patient.PatientHistory;
import com.dentaclinic.repository.patient.PatientHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientHistoryServices {

    private final PatientHistoryRepository patientHistoryRepository;

    public PatientHistoryServices(PatientHistoryRepository patientHistoryRepository) {
        this.patientHistoryRepository = patientHistoryRepository;
    }

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
