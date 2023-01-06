package com.dentaclinic.model.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
//@Data
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private int roleId;
    private String name;

    @OneToMany(mappedBy = "role", cascade= CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();
}
