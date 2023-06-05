package edu.utfpr.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class AlternativaRespostaId implements Serializable {
    private Resposta resposta;

    private Alternativa alternativa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlternativaRespostaId that = (AlternativaRespostaId) o;

        if (!Objects.equals(resposta, that.resposta)){
            return false;
        }
        if (!Objects.equals(alternativa, that.alternativa)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (resposta != null ? resposta.hashCode() : 0);
        result = 31 * result + (alternativa != null ? alternativa.hashCode() : 0);
        return result;
    }
}
