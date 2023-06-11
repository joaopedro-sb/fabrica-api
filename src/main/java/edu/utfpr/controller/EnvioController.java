package edu.utfpr.controller;

import edu.utfpr.entity.Envio;
import edu.utfpr.service.EnvioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import java.util.ArrayList;
import java.util.List;

@Path("/envio")
public class EnvioController {
    @Inject
    EnvioService envioService;

    @GET
    public List<Envio> retrieveAll() {
        List<Envio> envios = envioService.retrieveAll();
        try{
            envios = envioService.retrieveAll();
        }catch (Exception e) {
            e.printStackTrace();
        }

        envios.forEach(envio -> {
            envio.getPessoa().setQuestionarioList(null);
        });
        envios.forEach(envio -> {
            envio.getQuestionario().setQuestaoList(null);
        });
        envios.forEach(envio -> {
            envio.getRespostaList().forEach(resposta -> {
                resposta.setEnvio(null);
            });
        });

        return envioService.retrieveAll();
    }

    @GET
    @Path("/{id}")
    public Envio retrieve(Long id) {
        Envio envio = new Envio();
        try{
            envio = envioService.retrieve(id);
        }catch (Exception e) {
            e.printStackTrace();
        }

        envio.getPessoa().setQuestionarioList(null);
        envio.getQuestionario().setQuestaoList(null);
        envio.getRespostaList().forEach(resposta -> {
            resposta.setEnvio(null);
        });

        return envio;
    }

    @GET
    @Path("/questionario/{id}")
    public List<Envio> retrieveByQuestionario(@PathParam("id") Long id) {
        List<Envio> envios = new ArrayList<>();
        try{
            envios = envioService.retrieveByQuestionario(id);
        }catch (Exception e) {
            e.printStackTrace();
        }

        envios.forEach(envio -> {
            envio.getPessoa().setQuestionarioList(null);
        });
        envios.forEach(envio -> {
            envio.getQuestionario().setQuestaoList(null);
        });
        envios.forEach(envio -> {
            envio.getRespostaList().forEach(resposta -> {
                resposta.setEnvio(null);
            });
        });

        return envios;
    }

    @GET
    @Path("/pessoa/{id}")
    public List<Envio> retrieveByPessoa(@PathParam("id") Long id) {
        List<Envio> envios = new ArrayList<>();
        try{
            envios = envioService.retrieveByPessoa(id);
        }catch (Exception e) {
            e.printStackTrace();
        }

        envios.forEach(envio -> {
            envio.getPessoa().setQuestionarioList(null);
            envio.setQuestionario(null);
            envio.setPessoa(null);
            envio.getRespostaList().forEach(resposta -> {
                resposta.setEnvio(null);
                resposta.setQuestao(null);
                resposta.getAlternativaRespostaList().forEach(alternativaResposta -> {
                    alternativaResposta.getAlternativa().setQuestao(null);
                    alternativaResposta.getResposta().setEnvio(null);
                    alternativaResposta.getResposta().setQuestao(null);
                    alternativaResposta.getResposta().setAlternativaRespostaList(null);
                    alternativaResposta.setAlternativa(null);
                });
            });
        });

        return envios;
    }

    @POST
    @Transactional
    public Object create(Envio envio) {
        try{
            envioService.create(envio);

            envio.getRespostaList().forEach(resposta -> {
                resposta.setEnvio(null);
                //resposta.setQuestao(null);
            });

            return envio;
        }catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PUT
    @Transactional
    public Object update(Envio envio) {
        try{
            envioService.update(envio);

            return envio;
        }catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Object delete(@PathParam("id") Long id) {
        try{
            envioService.delete(id);

            return id;
        }catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
