package edu.utfpr.repository;

import edu.utfpr.entity.AlternativaResposta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class AlternativaRespostaRepository implements PanacheRepository<AlternativaResposta> {
    public AlternativaResposta findByAlternativaIdAndRespostaId(Long alternativaId, Long respostaId) {
        return find("alternativa_resposta.idalternativa = ?1 and alternativa_resposta.idresposta = ?2", alternativaId, respostaId).firstResult();
    }
}
