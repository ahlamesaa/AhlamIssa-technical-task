package com.luminous.integration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luminous.integration.model.Digimon;
@Repository

public interface CustomDigimonDAO extends JpaRepository<Digimon, Long> {

}
