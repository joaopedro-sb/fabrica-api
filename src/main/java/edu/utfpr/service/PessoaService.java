package edu.utfpr.service;

import edu.utfpr.entity.Pessoa;
import edu.utfpr.repository.PessoaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PessoaService {
    @Inject
    PessoaRepository pessoaRepository;

    public List<Pessoa> retrieveAll() {
        return pessoaRepository.listAll();
    }

    public Pessoa retrieve(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa retrieve(String email) {
        return pessoaRepository.findByEmail(email);
    }

    public void create(Pessoa pessoa) {
        pessoaRepository.persist(pessoa);
    }

    public void update(Pessoa pessoa) {
        if (pessoa.getId() == null) {
            throw new IllegalArgumentException("ID da pessoa não pode ser nulo");
        }
        Pessoa pessoaToUpdate = retrieve(pessoa.getId());
        if (pessoaToUpdate == null) {
            throw new IllegalArgumentException("Pessoa " + pessoa.getId() + " não encontrada");
        }
        pessoaToUpdate.setNome(pessoa.getNome());
        pessoaToUpdate.setCurso(pessoa.getCurso());
        pessoaToUpdate.setIdade(pessoa.getIdade());
        pessoaToUpdate.setIsProfessor(pessoa.getIsProfessor());
        pessoaToUpdate.setIsMonitor(pessoa.getIsMonitor());
        pessoaToUpdate.setRa(pessoa.getRa());
        pessoaToUpdate.setEmail(pessoa.getEmail());
        pessoaToUpdate.setPeriodo(pessoa.getPeriodo());
        pessoaToUpdate.setSenha(pessoa.getSenha());
        pessoaRepository.persist(pessoaToUpdate);
    }

    public void delete(Long id) {
        Pessoa pessoa = retrieve(id);
        if (pessoa != null) {
            pessoaRepository.delete(pessoa);
        } else {
            throw new IllegalArgumentException("Pessoa " + id + " não encontrada");
        }
    }
}
