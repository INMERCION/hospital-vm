package com.hospital_vm.cl.hospital_vm.repository;

import com.hospital_vm.cl.hospital_vm.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype. Repository;

import java.util.List;

@Repository

public interface PacienteRepository extends JpaRepository<Paciente, Long>  {
    //Usando JPQL
    //@Query("SELECT p FROM Paciente p WHERE p.apellidos= :apellidos")
    //List<Paciente> buscarPorApellidos(@Param("apellidos") String apellido);
    List<Paciente> findByApellidos(String apellidos);

    // Usando SQL nativo
    //@Query(value = "SELECT * FROM paciente WHERE correo = correo", nativeQuery = true)
    //Paciente buscarPorCorreo (@Param("correo") String correo);
    
    Paciente findByCorreo (String correo);

    List<Paciente> findByNombresAndApellidos(String nombres, String apellidos);
}
