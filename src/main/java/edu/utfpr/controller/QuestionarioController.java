package edu.utfpr.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.utfpr.entity.Questao;
import edu.utfpr.entity.Questionario;
import edu.utfpr.service.QuestionarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path("/questionario")
public class QuestionarioController {
    @Inject
    QuestionarioService questionarioService;

    @GET
    public List<Questionario> retrieveAll() {
        List<Questionario> questionarios = new ArrayList<>();
        try{
            questionarios = questionarioService.retrieveAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        questionarios.forEach(questionario -> {
//            questionario.getPessoa().setQuestionarioList(null);
//            questionario.getPessoa().setEnvioList(null);
//            questionario.getPessoa().setSenha(null);
//        });
//
//        questionarios.forEach(questionario -> {
//            questionario.getPessoa().setQuestionarioList(null);
//        });
//
//        questionarios.forEach(questionario -> {
//            questionario.getQuestaoList().forEach(questao -> {
//                questao.setQuestionario(null);
//            });
//        });
//
//        questionarios.forEach(questionario -> {
//            questionario.getQuestaoList().forEach(questao -> {
//                questao.getAlternativaList().forEach(alternativa -> {
//                    alternativa.setQuestao(null);
//                });
//            });
//        });
//
//        questionarios.forEach(questionario -> {
//            questionario.setEnvioList(null);
//        });
//
//        questionarios.forEach(questionario -> {
//            questionario.getQuestaoList().forEach(questao -> {
//                questao.setQuestionario(null);
//                questao.setRespostaList(null);
//            });
//        });

        return questionarios;
    }

    @GET
    @Path("/{id}")
    public Questionario retrieve(Long id) {
        Questionario questionario = new Questionario();
        try{
            questionario = questionarioService.retrieve(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        questionario.getPessoa().setQuestionarioList(null);
//        questionario.getPessoa().setEnvioList(null);
//        questionario.getPessoa().setSenha(null);
//        questionario.getQuestaoList().forEach(questao -> {
//            questao.setQuestionario(null);
//        });
//        questionario.getQuestaoList().forEach(questao -> {
//            questao.getAlternativaList().forEach(alternativa -> {
//                alternativa.setQuestao(null);
//            });
//        });
//        questionario.setEnvioList(null);
//        questionario.getQuestaoList().forEach(questao -> {
//            questao.setQuestionario(null);
//            questao.setRespostaList(null);
//        });

        return questionario;
    }

    @GET
    @Path("/pessoa/{id}")
    public List<Questionario> retrieveByPessoa(@PathParam("id") Long id) {
        List<Questionario> questionarios = new ArrayList<>();
        try{
            questionarios = questionarioService.retrieveByPessoa(id);

//            questionarios.forEach(questionario -> {
//                questionario.getPessoa().setQuestionarioList(null);
//                questionario.getPessoa().setEnvioList(null);
//                questionario.getPessoa().setSenha(null);
//                questionario.setEnvioList(null);
//                questionario.getQuestaoList().forEach(questao -> {
//                    questao.setQuestionario(null);
//                    questao.setRespostaList(null);
//                    questao.getAlternativaList().forEach(alternativa -> {
//                        alternativa.setQuestao(null);
//                    });
//                });
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionarios;
    }

    @POST
    @Transactional
    public Object create(Questionario questionario) {
        try{
            questionarioService.create(questionario);

            Questionario newQuestionario = new Questionario();

            questionario.getQuestaoList().forEach(questao -> {
                newQuestionario.setId(questao.getQuestionario().getId());
                newQuestionario.setTitulo(questao.getQuestionario().getTitulo());
                newQuestionario.setDescricao(questao.getQuestionario().getDescricao());
                newQuestionario.setCurso(questao.getQuestionario().getCurso());
                newQuestionario.setMateria(questao.getQuestionario().getMateria());
                newQuestionario.setNumeroTentativas(questao.getQuestionario().getNumeroTentativas());
                newQuestionario.setIsAtivo(questao.getQuestionario().getIsAtivo());
                newQuestionario.setIsOrdemAleatoria(questao.getQuestionario().getIsOrdemAleatoria());
                questao.setQuestionario(newQuestionario);

                Questao newQuestao = new Questao();

                questao.getAlternativaList().forEach(alternativa -> {
                    newQuestao.setId(alternativa.getQuestao().getId());
                    newQuestao.setEnunciado(alternativa.getQuestao().getEnunciado());
                    newQuestao.setDescritiva(alternativa.getQuestao().getDescritiva());
                    newQuestao.setPesoTotal(alternativa.getQuestao().getPesoTotal());
                    newQuestao.setMultiSelecao(alternativa.getQuestao().getMultiSelecao());
                    newQuestao.setIsOrdemAleatoria(alternativa.getQuestao().getIsOrdemAleatoria());
                    newQuestao.setDelete(null);
                    alternativa.setQuestao(null);
                });
            });

            return questionario;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PUT
    @Transactional
    public Object update(Questionario questionario) {
        try{
            questionarioService.update(questionario);
            Questionario newQuestionario = new Questionario();

            questionario.getQuestaoList().forEach(questao -> {
                newQuestionario.setId(questao.getQuestionario().getId());
                newQuestionario.setTitulo(questao.getQuestionario().getTitulo());
                newQuestionario.setDescricao(questao.getQuestionario().getDescricao());
                newQuestionario.setCurso(questao.getQuestionario().getCurso());
                newQuestionario.setMateria(questao.getQuestionario().getMateria());
                newQuestionario.setNumeroTentativas(questao.getQuestionario().getNumeroTentativas());
                newQuestionario.setIsAtivo(questao.getQuestionario().getIsAtivo());
                newQuestionario.setIsOrdemAleatoria(questao.getQuestionario().getIsOrdemAleatoria());
                questao.setQuestionario(newQuestionario);

                Questao newQuestao = new Questao();

                questao.getAlternativaList().forEach(alternativa -> {
                    newQuestao.setId(alternativa.getQuestao().getId());
                    newQuestao.setEnunciado(alternativa.getQuestao().getEnunciado());
                    newQuestao.setDescritiva(alternativa.getQuestao().getDescritiva());
                    newQuestao.setPesoTotal(alternativa.getQuestao().getPesoTotal());
                    newQuestao.setMultiSelecao(alternativa.getQuestao().getMultiSelecao());
                    newQuestao.setIsOrdemAleatoria(alternativa.getQuestao().getIsOrdemAleatoria());
                    newQuestao.setDelete(null);
                    alternativa.setQuestao(null);
                });
            });

            return questionario;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Object delete(@PathParam("id") Long id) {
        try{
            questionarioService.delete(id);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
