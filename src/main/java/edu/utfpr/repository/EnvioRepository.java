package edu.utfpr.repository;

import edu.utfpr.entity.Envio;
import edu.utfpr.entity.Questionario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EnvioRepository implements PanacheRepository<Envio> {
    public List<Envio> findByQuestionario(Long Id) {
        return list("questionario.id", Id);
    }

    public List<Envio> findByPessoa(Long Id) {
        return list("pessoa.id", Id);
    }
}
