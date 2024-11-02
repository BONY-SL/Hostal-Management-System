package com.hostalmanagement.Web.Application.util;

import com.hostalmanagement.Web.Application.model.Role;
import com.hostalmanagement.Web.Application.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultData {

    private final RoleRepository repository;

    String[] roleList = {"ADMIN","STUDENT","WARDEN","SUB_WARDEN","MAINTAIN_SUPERVISOR","DEAN"};


    @PostConstruct
    private void InitialRoles(){

        List<Role> departmentList = repository.findAll();

        if(departmentList.isEmpty()){
            for (String string : roleList) {
                repository.save(
                        Role.builder().name(string).build()
                );
            }
        }
    }
}
