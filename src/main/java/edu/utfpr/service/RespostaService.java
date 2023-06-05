package edu.utfpr.service;

import edu.utfpr.entity.Envio;
import edu.utfpr.entity.Questao;
import edu.utfpr.entity.Resposta;
import edu.utfpr.repository.AlternativaRepository;
import edu.utfpr.repository.EnvioRepository;
import edu.utfpr.repository.QuestaoRepository;
import edu.utfpr.repository.RespostaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class RespostaService {

    @Inject
    RespostaRepository respostaRepository;

    @Inject
    AlternativaRespostaService alternativaRespostaService;

    public List<Resposta> retrieveAll() {
        return respostaRepository.listAll();
    }

    public Resposta retrieve(Long id){
        return respostaRepository.findById(id);
    }

    public void create(Resposta resposta){
        if(resposta.getEnvio() == null){
            throw new IllegalArgumentException("A resposta precisa estar relacionada com um envio");
        } else {
            EnvioRepository envioRepository = new EnvioRepository();
            Envio envio = envioRepository.findById(resposta.getEnvio().getId());

            if (envio == null) {
                throw new IllegalArgumentException("Envio " + resposta.getEnvio().getId() + " não encontrado");
            } else {
                QuestaoRepository questaoRepository = new QuestaoRepository();
                Questao questao = questaoRepository.findById(resposta.getQuestao().getId());

                if (questao == null) {
                    throw new IllegalArgumentException("Questão " + resposta.getQuestao().getId() + " não encontrada");
                }
                resposta.setEnvio(envio);
                resposta.setQuestao(questao);
                respostaRepository.persist(resposta);

                if(resposta.getAlternativaRespostaList() != null) {
                    for (int i = 0; i < resposta.getAlternativaRespostaList().size(); i++) {
                        resposta.getAlternativaRespostaList().get(i).setResposta(resposta);
                        alternativaRespostaService.create(resposta.getAlternativaRespostaList().get(i));
                    }
                }
            }
        }
    }

    public void update(Resposta resposta){
        if(resposta.getId() == null){
            throw new IllegalArgumentException("ID da resposta não pode ser nulo");
        }
        Resposta respostaToUpdate = retrieve(resposta.getId());
        if(respostaToUpdate == null){
            throw new IllegalArgumentException("Resposta " + resposta.getId() + " não encontrada");
        }
        respostaToUpdate.setNota(resposta.getNota());
        respostaToUpdate.setDescritiva(resposta.getDescritiva());
        respostaRepository.persist(respostaToUpdate);

        if(resposta.getAlternativaRespostaList() != null){
            alternativaRespostaService.delete(resposta.getId());
            for(int i = 0; i < resposta.getAlternativaRespostaList().size(); i++){
                alternativaRespostaService.create(resposta.getAlternativaRespostaList().get(i));
            }
        }
    }

    public void delete(Long id){
        Resposta resposta = retrieve(id);
        if(resposta != null){
            respostaRepository.delete(resposta);
        }else {
            throw new IllegalArgumentException("Resposta " + id + " não encontrada");
        }
    }
}
