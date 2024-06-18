package com.elwynn94.onetomany.repository;

import com.elwynn94.onetomany.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Long> {
}