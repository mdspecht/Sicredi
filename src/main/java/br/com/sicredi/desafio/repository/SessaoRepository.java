package br.com.sicredi.desafio.repository;

import br.com.sicredi.desafio.model.Sessao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public interface SessaoRepository extends MongoRepository<Sessao, String> {
    Optional<Sessao> findByPautaId(String pautaId);

    List<Sessao> findByStatus(String status);
}
