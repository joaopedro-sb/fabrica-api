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
@Table(name = "resposta")
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idresposta", nullable = false)
    private Long id;

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

    @OneToMany(mappedBy = "resposta", fetch = FetchType.LAZY)
    private List<AlternativaResposta> alternativaRespostaList;

    @Transient
    private Boolean delete = false;
}
