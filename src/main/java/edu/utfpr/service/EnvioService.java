package edu.utfpr.service;

import edu.utfpr.entity.Envio;
import edu.utfpr.entity.Pessoa;
import edu.utfpr.repository.EnvioRepository;
import edu.utfpr.repository.PessoaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class EnvioService {
    @Inject
    EnvioRepository envioRepository;

    @Inject
    QuestionarioService questionarioService;

    @Inject
    RespostaService respostaService;

    public List<Envio> retrieveAll() {
        return envioRepository.findAll().list();
    }

    public Envio retrieve(Long id){
        return envioRepository.findById(id);
    }

    public List<Envio> retrieveByQuestionario(Long id){
        return envioRepository.findByQuestionario(id);
    }

    public List<Envio> retrieveByPessoa(Long id){
        return envioRepository.findByPessoa(id);
    }

    public void create(Envio envio){
        if (envio.getPessoa() == null || envio.getPessoa().getId() == null){
            throw new IllegalArgumentException("Pessoa não pode ser nula");
        }else{
            PessoaRepository pessoaRepository = new PessoaRepository();
            Pessoa pessoa = pessoaRepository.findById(envio.getPessoa().getId());
            if(pessoa == null){
                throw new IllegalArgumentException("Pessoa " + envio.getPessoa().getId() + " não encontrada");
            }
        }
        if(envio.getQuestionario() == null || envio.getQuestionario().getId() == null) {
            throw new IllegalArgumentException("Questionário não pode ser nulo");
        }else {
            if(questionarioService.retrieve(envio.getQuestionario().getId()) == null){
                throw new IllegalArgumentException("Questionário " + envio.getQuestionario().getId() + " não encontrado");
            }
        }
        envioRepository.persist(envio);

        if(envio.getRespostaList() != null){
            for(int i = 0; i < envio.getRespostaList().size(); i++){
                envio.getRespostaList().get(i).setEnvio(envio);
                respostaService.create(envio.getRespostaList().get(i));
            }
        }
    }

    public void update(Envio envio){
        if(envio.getId() == null){
            throw new IllegalArgumentException("ID do envio não pode ser nulo");
        }
        Envio envioToUpdate = retrieve(envio.getId());
        if(envioToUpdate == null){
            throw new IllegalArgumentException("Envio " + envio.getId() + " não encontrado");
        }
        envioToUpdate.setNotaTotal(envio.getNotaTotal());
        envioToUpdate.setFeedback(envio.getFeedback());
        envioRepository.persist(envioToUpdate);

        if(envio.getRespostaList() != null){
            for(int i = 0; i < envio.getRespostaList().size(); i++){
                if(envio.getRespostaList().get(i).getDelete()){
                    respostaService.delete(envio.getRespostaList().get(i).getId());
                } else {
                    if(envio.getRespostaList().get(i).getId() == null){
                        envio.getRespostaList().get(i).setEnvio(envioToUpdate);
                        respostaService.create(envio.getRespostaList().get(i));
                    } else {
                        respostaService.update(envio.getRespostaList().get(i));
                    }
                }
            }
        }
    }

    public void delete(Long id){
        Envio envio = retrieve(id);
        if(envio == null){
            throw new IllegalArgumentException("Envio " + id + " não encontrado");
        }
        envioRepository.delete(envio);
    }
}
