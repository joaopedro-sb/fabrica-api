package edu.utfpr.controller;

import edu.utfpr.entity.Questionario;
import edu.utfpr.service.QuestionarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import java.util.ArrayList;
import java.util.List;

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

        questionarios.forEach(questionario -> {
            questionario.getPessoa().setQuestionarioList(null);
        });

        questionarios.forEach(questionario -> {
            questionario.getQuestaoList().forEach(questao -> {
                questao.setQuestionario(null);
            });
        });

        questionarios.forEach(questionario -> {
            questionario.getQuestaoList().forEach(questao -> {
                questao.getAlternativaList().forEach(alternativa -> {
                    alternativa.setQuestao(null);
                });
            });
        });

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
        questionario.getPessoa().setQuestionarioList(null);
        questionario.getQuestaoList().forEach(questao -> {
            questao.setQuestionario(null);
        });
        questionario.getQuestaoList().forEach(questao -> {
            questao.getAlternativaList().forEach(alternativa -> {
                alternativa.setQuestao(null);
            });
        });

        return questionario;
    }

    @POST
    @Transactional
    public Object create(Questionario questionario) {
        try{
            questionarioService.create(questionario);

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
            questionario.getPessoa().setQuestionarioList(null);
            questionario.getQuestaoList().forEach(questao -> {
                questao.setQuestionario(null);
            });
            questionario.getQuestaoList().forEach(questao -> {
                questao.getAlternativaList().forEach(alternativa -> {
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
