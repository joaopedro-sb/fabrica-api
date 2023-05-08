package edu.utfpr.repository;

import edu.utfpr.entity.Questionario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuestionarioRepository implements PanacheRepository<Questionario> {

}
