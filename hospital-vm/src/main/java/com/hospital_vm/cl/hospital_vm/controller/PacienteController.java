package com.hospital_vm.cl.hospital_vm.controller;

import com.hospital_vm.cl.hospital_vm.model.Paciente;
import com.hospital_vm.cl.hospital_vm.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {
    
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity <List<Paciente>> listar(){
        List<Paciente> pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            return ResponseEntity.noContent().build(); //alternative 2 return new ResponseEntity>(HttpStatus.NO CONTENT);
        }
        return ResponseEntity.ok(pacientes); //alternative 3 return new ResponseEntity<>(pacientes, HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar (@RequestBody Paciente paciente) {
        Paciente productoMuevo = pacienteService.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoMuevo);
        //return new ResponseEntity<>(productoMuevo, HttpStatus.ACCEPTED);
    
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Long id) {
        try {
            Paciente paciente = pacienteService.FindById( id);
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
        try {
            Paciente pac = pacienteService.FindById( id);
            pac.setId( id.intValue());
            pac.setRun(paciente.getRun());
            pac.setNombres (paciente.getNombres());
            pac.setApellidos(paciente.getApellidos());
            pac.setFechaNacimiento (paciente.getFechaNacimiento());
            pac.setCorreo(paciente.getCorreo());
            pacienteService.save(pac);
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar (@PathVariable Long id) {
        try {
            pacienteService.delete(id);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {

            return ResponseEntity.notFound().build();
        }
    }
}