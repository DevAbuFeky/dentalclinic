package com.dentaclinic.model.patient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "PATIENT_HISTORY_TABLE")
@AllArgsConstructor
@NoArgsConstructor
public class PatientHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;
    private String patientMobile;
    private String patientGender;
    private String patientBirthday;
    private String patientJob;
    private String visitReason;
    private String allergicToQuestions;
    private String allergicToWhyQuestions;
    private String followUpDate;
    private String visitDate;
    private String fileNumber;
    private Boolean active;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "PATIENT_HISTORY_AND_QUESTIONS_TEBLE",
    joinColumns = {
            @JoinColumn(name = "patient_history_id", referencedColumnName = "id")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "patient_questions_id", referencedColumnName = "id")
    })
    private Set<PatientHistoryQuestions> patientHistoryQuestions = new HashSet<>();

}