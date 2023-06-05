package edu.utfpr.service;

import edu.utfpr.entity.Envio;
import edu.utfpr.repository.EnvioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class EnvioService {
    @Inject
    EnvioRepository envioRepository;

    @Inject
    RespostaService respostaService;

    public List<Envio> retrieveAll() {
        return envioRepository.findAll().list();
    }

    public Envio retrieve(Long id){
        return envioRepository.findById(id);
    }

    public void create(Envio envio){
        envioRepository.persist(envio);
    }

    public void update(Envio envio){
        if(envio.getId() == null){
            throw new IllegalArgumentException("ID do envio não pode ser nulo");
        }
        Envio envioToUpdate = retrieve(envio.getId());
        if(envioToUpdate == null){
            throw new IllegalArgumentException("Envio " + envio.getId() + " não encontrado");
        }
        envioToUpdate.setNotaTotal(envio.getNotaTotal());
        envioToUpdate.setFeedback(envio.getFeedback());
        envioRepository.persist(envioToUpdate);

        if(envio.getRespostaList() != null){
            for(int i = 0; i < envio.getRespostaList().size(); i++){
                if(envio.getRespostaList().get(i).getDelete()){
                    respostaService.delete(envio.getRespostaList().get(i).getId());
                } else {
                    if(envio.getRespostaList().get(i).getId() == null){
                        envio.getRespostaList().get(i).setEnvio(envioToUpdate);
                        respostaService.create(envio.getRespostaList().get(i));
                    } else {
                        respostaService.update(envio.getRespostaList().get(i));
                    }
                }
            }
        }
    }
}
