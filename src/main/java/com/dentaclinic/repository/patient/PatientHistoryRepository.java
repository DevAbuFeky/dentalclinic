package com.dentaclinic.repository.patient;

import com.dentaclinic.model.patient.PatientHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientHistoryRepository extends JpaRepository<PatientHistory,Long> {
}
