package com.ulisse.crud_ulisse.repository;

import com.ulisse.crud_ulisse.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
