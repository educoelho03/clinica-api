package com.cliniconect.clinicaapi.repository;

import com.cliniconect.clinicaapi.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("select p from Paciente p where p.nome LIKE %?1%")
    List<Paciente> buscaNome(String nome);

    @Query("select p from Paciente p where p.cpf LIKE %?1%")
    List<Paciente> buscaCPF(String cpf);

    @Query("select p from Paciente p where p.email LIKE %?1%")
    List<Paciente> buscaEmail(String email);
}
