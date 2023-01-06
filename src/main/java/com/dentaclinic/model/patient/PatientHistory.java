package com.dentaclinic.model.patient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
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

//    @OneToMany
//    @JsonIgnore
//    private Set<PatientHistoryQuestions> patientHistoryQuestions = new HashSet<>();
}