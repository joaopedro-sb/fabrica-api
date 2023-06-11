package edu.utfpr.repository;

import edu.utfpr.entity.AlternativaResposta;
import edu.utfpr.entity.Questionario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class QuestionarioRepository implements PanacheRepository<Questionario> {
    public List<Questionario> findByPessoa(Long Id) {
        return list("pessoa.id", Id);
    }
}
