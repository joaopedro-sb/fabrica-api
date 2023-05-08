package edu.utfpr.entity;

import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;
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
@Table(name = "questoes")
public class Questao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idquestao", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idquestionario", nullable = false)
    private Questionario questionario;

    @Column(name = "enunciado", nullable = false, length = 500)
    private String enunciado;

    @Column(name = "descritiva", length = 32000)
    private String descritiva;

    @Column(name = "pesototal", nullable = false)
    private Double pesoTotal;

    @Column(name = "multiselecao")
    @DefaultValue("false")
    private Boolean multiSelecao = false;

    @Column(name = "ordemaleatoria")
    @DefaultValue("false")
    private Boolean isOrdemAleatoria = false;

    @OneToMany(mappedBy = "questao", fetch = FetchType.LAZY)
    private List<Alternativa> alternativaList;

    @Transient
    private Boolean delete = false;
}
