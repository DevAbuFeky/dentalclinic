package com.dentaclinic.repository.patient;

import com.dentaclinic.model.patient.PatientHistoryQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PatientHistoryQuestionsRepository extends JpaRepository<PatientHistoryQuestions, Long> {

    Optional<PatientHistoryQuestions> findById(Long id);
}
