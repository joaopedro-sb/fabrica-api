package edu.utfpr.repository;

import edu.utfpr.entity.Questao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuestaoRepository implements PanacheRepository<Questao> {

}
