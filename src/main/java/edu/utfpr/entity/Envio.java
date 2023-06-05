package edu.utfpr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "envio")
public class Envio {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "idenvio", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "idpessoa", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "idquestionario", nullable = false)
    private Questionario questionario;

    @Column(name = "notatotal", nullable = false, precision = 2)
    private Double notaTotal;

    @Column(name = "feedback", length = 500)
    private String feedback;

    @Column(name = "datahorainicio", nullable = false)
    private Timestamp dataHoraInicio;

    @Column(name = "datahorafim")
    private Timestamp dataHoraFim;

    @OneToMany(mappedBy = "envio", fetch = FetchType.LAZY)
    private List<Resposta> respostaList;
}
