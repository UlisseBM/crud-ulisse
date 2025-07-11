package com.ulisse.crud_ulisse.controller;

import com.ulisse.crud_ulisse.model.Persona;
import com.ulisse.crud_ulisse.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final PersonaService service;

    public PersonaController(PersonaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Persona> getAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getById(@PathVariable Long id) {
        return service.searchById(id)
                .map(persona -> ResponseEntity.ok().body(persona))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Persona> create(@Valid @RequestBody Persona persona) {
        Persona created = service.save(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Persona>> createMany(@RequestBody List<@Valid Persona> people) {
        List<Persona> saved = people.stream()
                .map(service::save)
                .toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizar(@PathVariable Long id, @RequestBody Persona persona) {
        return service.searchById(id)
                .map(p -> {
                    p.setName(persona.getName());
                    p.setEmail(persona.getEmail());
                    Persona updated = service.save(p);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        if (service.searchById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

