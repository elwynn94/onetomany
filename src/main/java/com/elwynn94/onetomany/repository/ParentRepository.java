package com.elwynn94.onetomany.repository;

import com.elwynn94.onetomany.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
}