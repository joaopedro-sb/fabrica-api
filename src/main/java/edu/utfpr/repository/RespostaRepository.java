package edu.utfpr.repository;

import edu.utfpr.entity.Resposta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RespostaRepository implements PanacheRepository<Resposta> {

}
