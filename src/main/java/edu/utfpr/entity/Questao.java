package edu.utfpr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.runtime.annotations.IgnoreProperty;
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
@Table(name = "questao")
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

    @JsonIgnoreProperties({"questao"})
    @OneToMany(mappedBy = "questao", fetch = FetchType.LAZY)
    private List<Alternativa> alternativaList;

    @JsonIgnore
    @OneToMany(mappedBy = "questao", fetch = FetchType.LAZY)
    private List<Resposta> respostaList;

    @JsonIgnore
    @Transient
    private Boolean delete = false;
}
