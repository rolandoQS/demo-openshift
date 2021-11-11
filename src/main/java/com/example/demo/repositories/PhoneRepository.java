package com.example.demo.repositories;

import com.example.demo.entities.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {



}
