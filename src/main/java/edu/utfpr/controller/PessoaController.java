package edu.utfpr.controller;

import edu.utfpr.entity.Pessoa;
import edu.utfpr.service.PessoaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.jasypt.util.password.BasicPasswordEncryptor;

import java.util.ArrayList;
import java.util.List;

@Path("/pessoa")
public class PessoaController {
    @Inject
    PessoaService pessoaService;

    @GET
    public List<Pessoa> retrieveAll() {
        List<Pessoa> pessoas = new ArrayList<>();
        try{
            pessoas = pessoaService.retrieveAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        pessoas.forEach(pessoa -> {
            pessoa.setQuestionarioList(null);
        });

        return pessoas;
    }

    @GET
    @Path("/{id}")
    public Pessoa retrieve(Long id) {
        Pessoa pessoa = new Pessoa();
        try{
            pessoa = pessoaService.retrieve(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        pessoa.setQuestionarioList(null);

        return pessoa;
    }

    @GET
    @Path("/login/{email}/{senha}")
    public Pessoa retrieve(@PathParam("email") String email, @PathParam("senha") String senha) {
        Pessoa pessoa = new Pessoa();
        try{
            pessoa = pessoaService.retrieve(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        pessoa.setQuestionarioList(null);

        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        if(!passwordEncryptor.checkPassword(senha, pessoa.getSenha())){
            pessoa = null;
        }

        return pessoa;
    }

    @POST
    @Transactional
    public Object create(Pessoa pessoa) {
        try{
            BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
            pessoa.setSenha(passwordEncryptor.encryptPassword(pessoa.getSenha()));

            pessoaService.create(pessoa);
            pessoa.setQuestionarioList(null);

            return pessoa;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PUT
    @Transactional
    public Object update(Pessoa pessoa) {
        try{
            pessoaService.update(pessoa);
            pessoa.setQuestionarioList(null);

            return pessoa;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public String delete(Long id) {
        try{
            pessoaService.delete(id);
            return "Pessoa " + id + " deletada com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
