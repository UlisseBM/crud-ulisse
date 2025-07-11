package com.ulisse.crud_ulisse.service;

import com.ulisse.crud_ulisse.model.Persona;
import com.ulisse.crud_ulisse.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository repo;

    public PersonaService(PersonaRepository repo) {
        this.repo = repo;
    }

    public List<Persona> listAll() {
        return repo.findAll();
    }

    public Optional<Persona> searchById(Long id) {
        return repo.findById(id);
    }

    public Persona save(Persona persona) {
        return repo.save(persona);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
