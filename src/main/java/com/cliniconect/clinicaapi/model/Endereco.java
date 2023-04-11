package com.cliniconect.clinicaapi.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rua;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
