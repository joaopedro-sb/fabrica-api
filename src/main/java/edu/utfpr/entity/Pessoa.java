package edu.utfpr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpessoa", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "curso", length = 100)
    private String curso;

    @Column(name = "idade", nullable = false)
    private Integer idade;

    @Column(name = "professor")
    private Boolean isProfessor = false;

    @Column(name = "monitor")
    private Boolean isMonitor = false;

    @Column(name = "ra", nullable = false, length = 7)
    private String ra;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "periodo")
    private Integer periodo;

    @JsonIgnore
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @JsonIgnore
    @Column(name = "imagem")
    private Blob imagem;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
    private List<Questionario> questionarioList;

    @JsonIgnore
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
    private List<Envio> envioList;
}
