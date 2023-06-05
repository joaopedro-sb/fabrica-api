package edu.utfpr.service;

import edu.utfpr.entity.AlternativaResposta;
import edu.utfpr.repository.AlternativaRespostaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class AlternativaRespostaService {
    @Inject
    AlternativaRespostaRepository alternativaRespostaRepository;

    public List<AlternativaResposta> retrieveAll() {
        return alternativaRespostaRepository.listAll();
    }

    public List<AlternativaResposta> retrieve(Long idResposta) {
        return alternativaRespostaRepository.list("resposta.id = ?1", idResposta);
    }

    public AlternativaResposta retrieve(Long idResposta, Long idAlternativa) {
        return alternativaRespostaRepository.findByAlternativaIdAndRespostaId(idAlternativa, idResposta);
    }

    public void create(AlternativaResposta alternativaResposta) {
        alternativaRespostaRepository.persist(alternativaResposta);
    }

    public void update(AlternativaResposta alternativaResposta) {
        if (alternativaResposta.getAlternativa() == null) {
            throw new IllegalArgumentException("Alternativa selecionada para resposta precisa estar relacionada com uma alternativa existente");
        }else if (alternativaResposta.getResposta() == null) {
            throw new IllegalArgumentException("Alternativa selecionada precisa estar relacionada com uma resposta");
        }
        AlternativaResposta alternativaRespostaToUpdate = retrieve(alternativaResposta.getResposta().getId(), alternativaResposta.getAlternativa().getId());
        if (alternativaRespostaToUpdate == null) {
            throw new IllegalArgumentException("Alternativa selecionada não encontrada");
        }
        alternativaRespostaToUpdate.setResposta(alternativaResposta.getResposta());
        alternativaRespostaToUpdate.setAlternativa(alternativaResposta.getAlternativa());
        alternativaRespostaRepository.persist(alternativaRespostaToUpdate);
    }

    public void delete(Long idResposta, Long idAlternativa) {
        AlternativaResposta alternativaResposta = retrieve(idResposta, idAlternativa);
        if (alternativaResposta == null) {
            throw new IllegalArgumentException("Alternativa selecionada não encontrada");
        }
        alternativaRespostaRepository.delete(alternativaResposta);
    }

    public void delete(Long idResposta) {
        List<AlternativaResposta> alternativaResposta = retrieve(idResposta);
        if (alternativaResposta.size() == 0) {
            throw new IllegalArgumentException("Alternativa selecionada não encontrada");
        }
        alternativaRespostaRepository.delete("alternativa_resposta.idresposta = ?1", idResposta);
    }
}
