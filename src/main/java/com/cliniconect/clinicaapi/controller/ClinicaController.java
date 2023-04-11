package com.cliniconect.clinicaapi.controller;

import com.cliniconect.clinicaapi.model.Paciente;
import com.cliniconect.clinicaapi.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/clinica")
public class ClinicaController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping()
    public List<Paciente> read(){
        return pacienteRepository.findAll();
    }

    @GetMapping("pag")
    public ResponseEntity<Page<Paciente>> read(Pageable pageable) {
        Page<Paciente> pacientes = pacienteRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("nome/{nome}")
    public List<Paciente> buscaNome(@PathVariable String nome){
        return pacienteRepository.buscaNome(nome);
    }

    @GetMapping("cpf/{cpf}")
    public List<Paciente> buscaCPF(@PathVariable String cpf){
        return pacienteRepository.buscaCPF(cpf);
    }

    @GetMapping("email/{email}")
    public List<Paciente> buscaEmail(@PathVariable String email){
        return pacienteRepository.buscaEmail(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente create(@RequestBody Paciente paciente){
        System.out.println(paciente.getEnderecos());

        return pacienteRepository.save(paciente);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Paciente> update(@PathVariable Long id, @RequestBody Paciente paciente){
        if (paciente == null) {
            return ResponseEntity.badRequest().build();
        }

        Paciente p = pacienteRepository.findById(id).orElseThrow();

        p.setNome(paciente.getNome());
        p.setSexo(paciente.getSexo());
        p.setCpf(paciente.getCpf());
        p.setCelular(paciente.getCelular());
        p.setEmail(paciente.getEmail());
        p.setDataNascimento(paciente.getDataNascimento());
        p.setDescricao(paciente.getDescricao());

        pacienteRepository.saveAndFlush(p);

        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id){
        Paciente p = pacienteRepository.findById(id).orElseThrow();
        pacienteRepository.delete(p);
        return ResponseEntity.ok(id);
    }
}
