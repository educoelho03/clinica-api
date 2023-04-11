package com.cliniconect.clinicaapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    @NotNull
    private String sexo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past(message = "A data de nascimento deve estar no passado.")
    private LocalDate DataNascimento;

    @OneToMany(mappedBy = "paciente")
    private List<Endereco> enderecos = new ArrayList<Endereco>();

    @CPF
    @NotNull
    private String cpf;

    @NotBlank
    @NotNull
    @Pattern(regexp = "\\\\(\\\\d{2}\\\\)\\\\d{4,5}-\\\\d{4}", message = "Número de celular inválido.")
    private String celular;

    @Email
    @NotNull
    private String email;

    @Column(nullable = false)
    private String descricao;
}
