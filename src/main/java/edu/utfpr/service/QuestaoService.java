package edu.utfpr.service;

import edu.utfpr.entity.Questao;
import edu.utfpr.repository.QuestaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class QuestaoService {
    @Inject
    QuestaoRepository questaoRepository;

    @Inject
    AlternativaService alternativaService;

    public List<Questao> retrieveAll() {
        return questaoRepository.findAll().list();
    }

    public Questao retrieve(Long id) {
        return questaoRepository.findById(id);
    }

    public void create(Questao questao) {
        questaoRepository.persist(questao);

        if(questao.getAlternativaList() != null){
            for(int i = 0; i < questao.getAlternativaList().size(); i++){
                questao.getAlternativaList().get(i).setQuestao(questao);
                alternativaService.create(questao.getAlternativaList().get(i));
            }
        }
    }

    public void update(Questao questao) {
        if (questao.getId() == null) {
            throw new IllegalArgumentException("ID da questão não pode ser nulo");
        }
        Questao questaoToUpdate = retrieve(questao.getId());
        if (questaoToUpdate == null) {
            throw new IllegalArgumentException("Questão " + questao.getId() + " não encontrada");
        }
        questaoToUpdate.setEnunciado(questao.getEnunciado());
        questaoToUpdate.setDescritiva(questao.getDescritiva());
        questaoToUpdate.setPesoTotal(questao.getPesoTotal());
        questaoToUpdate.setMultiSelecao(questao.getMultiSelecao());
        questaoToUpdate.setIsOrdemAleatoria(questao.getIsOrdemAleatoria());
        questaoRepository.persist(questaoToUpdate);

        if(questao.getAlternativaList() != null) {
            for (int i = 0; i < questao.getAlternativaList().size(); i++) {
                if (questao.getAlternativaList().get(i).getDelete()) {
                    alternativaService.delete(questao.getAlternativaList().get(i).getId());
                } else {
                    if (questao.getAlternativaList().get(i).getId() == null) {
                        questao.getAlternativaList().get(i).setQuestao(questaoToUpdate);
                        alternativaService.create(questao.getAlternativaList().get(i));
                    } else {
                        alternativaService.update(questao.getAlternativaList().get(i));
                    }
                }
            }
        }
    }

    public void delete(Long id) {
        Questao questao = retrieve(id);
        if (questao != null) {
            questaoRepository.delete(questao);
        } else {
            throw new IllegalArgumentException("Questão " + id + " não encontrada");
        }
    }
}
