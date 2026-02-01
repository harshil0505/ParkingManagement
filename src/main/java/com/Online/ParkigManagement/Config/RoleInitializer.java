package com.Online.ParkigManagement.Config;

import org.springframework.stereotype.Component;

import com.Online.ParkigManagement.Repository.RoleRepository;
import com.Online.ParkigManagement.model.AppRole;
import com.Online.ParkigManagement.model.Role;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class RoleInitializer {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
        for (AppRole role : AppRole.values()) {
            roleRepository.findByRoleName(role)
                .orElseGet(() -> roleRepository.save(new Role(null, role)));
        }
    }
}


