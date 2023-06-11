package edu.utfpr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "alternativa")
public class Alternativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idalternativa", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idquestao", nullable = false)
    private Questao questao;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @Column(name = "correta", columnDefinition = "boolean default false")
    @DefaultValue("false")
    private Boolean isCorreta = false;

    @Column(name = "peso", precision = 2)
    private Double peso;

    @Column(name = "justificativa", length = 32000)
    private String justificativa;

    @JsonIgnore
    @Transient
    private Boolean delete = false;
}
