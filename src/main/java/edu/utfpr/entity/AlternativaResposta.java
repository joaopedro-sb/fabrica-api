package edu.utfpr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "alternativa_resposta")
@IdClass(AlternativaRespostaId.class)
public class AlternativaResposta {
    @Id
    @ManyToOne
    @JoinColumn(name = "idresposta", nullable = false)
    private Resposta resposta;

    @Id
    @ManyToOne
    @JoinColumn(name = "idalternativa", nullable = false)
    private Alternativa alternativa;
}
