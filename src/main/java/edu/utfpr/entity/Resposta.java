package edu.utfpr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "resposta")
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idresposta", nullable = false)
    private Long id;

    @JsonIgnoreProperties({"respostaList", "pessoa", "questionario"})
    @ManyToOne
    @JoinColumn(name = "idenvio", nullable = false)
    private Envio envio;

    @ManyToOne
    @JoinColumn(name = "idquestao", nullable = false)
    private Questao questao;

    @Column(name = "nota", nullable = false, precision = 2)
    private Double nota;

    @Column(name = "descritiva", length = 32000)
    private String descritiva;

    @JsonIgnore
    @OneToMany(mappedBy = "resposta", fetch = FetchType.LAZY)
    private List<AlternativaResposta> alternativaRespostaList;

    @JsonIgnore
    @Transient
    private Boolean delete = false;
}
