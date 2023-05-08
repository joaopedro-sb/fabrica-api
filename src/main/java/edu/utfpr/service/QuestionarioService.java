package edu.utfpr.service;

import edu.utfpr.entity.Pessoa;
import edu.utfpr.entity.Questionario;
import edu.utfpr.repository.PessoaRepository;
import edu.utfpr.repository.QuestionarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class QuestionarioService {
    @Inject
    QuestionarioRepository questionarioRepository;

    @Inject
    QuestaoService questaoService;

    public List<Questionario> retrieveAll() {
        return questionarioRepository.listAll();
    }

    public Questionario retrieve(Long id) {
        return questionarioRepository.findById(id);
    }

    public void create(Questionario questionario) {
        if(questionario.getPessoa() == null){
            throw new IllegalArgumentException("ID da pessoa não pode ser nulo");
        }else {
            PessoaRepository pessoaRepository = new PessoaRepository();
            Pessoa pessoa = pessoaRepository.findById(questionario.getPessoa().getId());
            if (pessoa == null) {
                throw new IllegalArgumentException("Pessoa " + questionario.getPessoa().getId() + " não encontrada");
            }
        }
        questionario.setPessoa(questionario.getPessoa());
        questionarioRepository.persist(questionario);

        if(questionario.getQuestaoList() != null){
            for(int i = 0; i < questionario.getQuestaoList().size(); i++){
                questionario.getQuestaoList().get(i).setQuestionario(questionario);
                questaoService.create(questionario.getQuestaoList().get(i));
            }
        }
    }

    public void update(Questionario questionario) {
        if (questionario.getId() == null) {
            throw new IllegalArgumentException("ID do questionario não pode ser nulo");
        }
        Questionario questionarioToUpdate = retrieve(questionario.getId());
        if (questionarioToUpdate == null) {
            throw new IllegalArgumentException("Questionario " + questionario.getId() + " não encontrado");
        }
        questionarioToUpdate.setId(questionario.getId());
        questionarioToUpdate.setTitulo(questionario.getTitulo());
        questionarioToUpdate.setDescricao(questionario.getDescricao());
        questionarioToUpdate.setCurso(questionario.getCurso());
        questionarioToUpdate.setMateria(questionario.getMateria());
        questionarioToUpdate.setIsAtivo(questionario.getIsAtivo());
        questionarioToUpdate.setIsOrdemAleatoria(questionario.getIsOrdemAleatoria());
        questionarioRepository.persist(questionarioToUpdate);

        if(questionario.getQuestaoList() != null) {
            for (int i = 0; i < questionario.getQuestaoList().size(); i++) {
                if (questionario.getQuestaoList().get(i).getDelete()) {
                    questaoService.delete(questionario.getQuestaoList().get(i).getId());
                } else {
                    if (questionario.getQuestaoList().get(i).getId() == null) {
                        questionario.getQuestaoList().get(i).setQuestionario(questionarioToUpdate);
                        questaoService.create(questionario.getQuestaoList().get(i));
                    } else {
                        questaoService.update(questionario.getQuestaoList().get(i));
                    }
                }
            }
        }
    }

    public void delete(Long id) {
        Questionario questionario = retrieve(id);
        if (questionario != null) {
            questionarioRepository.delete(questionario);
        } else {
            throw new IllegalArgumentException("Questionario " + id + " não encontrado");
        }
    }
}
