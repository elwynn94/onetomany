package com.elwynn94.onetomany.service;

import com.elwynn94.onetomany.entity.Parent;
import com.elwynn94.onetomany.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParentService {
    private final ParentRepository parentRepository;

    @Autowired
    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public Parent saveParentWithChildren(Parent parent) {
        return parentRepository.save(parent);
    }
}