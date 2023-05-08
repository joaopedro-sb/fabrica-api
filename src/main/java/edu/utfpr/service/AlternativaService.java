package edu.utfpr.service;

import edu.utfpr.entity.Alternativa;
import edu.utfpr.repository.AlternativaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class AlternativaService {
    @Inject
    AlternativaRepository alternativaRepository;

    public List<Alternativa> retrieveAll() {
        return alternativaRepository.findAll().list();
    }

    public Alternativa retrieve(Long id) {
        return alternativaRepository.findById(id);
    }

    public void create(Alternativa alternativa) {
        alternativaRepository.persist(alternativa);
    }

    public void update(Alternativa alternativa) {
        if (alternativa.getId() == null) {
            throw new IllegalArgumentException("ID da alternativa não pode ser nulo");
        }
        Alternativa alternativaToUpdate = retrieve(alternativa.getId());
        if (alternativaToUpdate == null) {
            throw new IllegalArgumentException("Alternativa " + alternativa.getId() + " não encontrada");
        }
        alternativaToUpdate.setDescricao(alternativa.getDescricao());
        alternativaToUpdate.setIsCorreta(alternativa.getIsCorreta());
        alternativaToUpdate.setPeso(alternativa.getPeso());
        alternativaToUpdate.setJustificativa(alternativa.getJustificativa());
        alternativaRepository.persist(alternativaToUpdate);
    }

    public void delete(Long id) {
        Alternativa alternativa = retrieve(id);
        if (alternativa != null) {
            alternativaRepository.delete(alternativa);
        } else {
            throw new IllegalArgumentException("Alternativa " + id + " não encontrada");
        }
    }
}
