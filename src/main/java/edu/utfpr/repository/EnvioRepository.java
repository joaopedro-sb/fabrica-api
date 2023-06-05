package edu.utfpr.repository;

import edu.utfpr.entity.Envio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnvioRepository implements PanacheRepository<Envio> {

}
