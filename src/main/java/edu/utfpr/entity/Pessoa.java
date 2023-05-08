package edu.utfpr.entity;

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
    private Boolean isProfessor;

    @Column(name = "monitor")
    private Boolean isMonitor;

    @Column(name = "ra", nullable = false, length = 7)
    private String ra;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "periodo")
    private Integer periodo;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Questionario> questionarioList;
}
