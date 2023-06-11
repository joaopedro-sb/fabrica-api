package edu.utfpr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "questionario")
public class Questionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idquestionario", nullable = false)
    private Long id;

    @JsonIgnoreProperties({"questionarioList", "envioList"})
    @ManyToOne
    @JoinColumn(name = "idcriador", nullable = false)
    private Pessoa pessoa;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "curso", length = 100)
    private String curso;

    @Column(name = "materia", length = 100)
    private String materia;

    @Column(name = "numerotentativas", nullable = false)
    private Integer numeroTentativas = 1;

    @Column(name = "ativo")
    private Boolean isAtivo = true;

    @Column(name = "ordemaleatoria")
    private Boolean isOrdemAleatoria = false;

    @JsonIgnoreProperties({"questionario"})
    @OneToMany(mappedBy = "questionario", fetch = FetchType.LAZY)
    private List<Questao> questaoList;

    @JsonIgnore
    @OneToMany(mappedBy = "questionario", fetch = FetchType.LAZY)
    private List<Envio> envioList;
}
