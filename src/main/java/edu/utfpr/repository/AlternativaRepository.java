package edu.utfpr.repository;

import edu.utfpr.entity.Alternativa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlternativaRepository implements PanacheRepository<Alternativa> {

}
