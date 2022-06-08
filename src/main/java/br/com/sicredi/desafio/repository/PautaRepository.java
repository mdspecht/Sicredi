package br.com.sicredi.desafio.repository;

import br.com.sicredi.desafio.model.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PautaRepository extends MongoRepository<Pauta, String> {
    Optional<Pauta> findByTitulo(String titulo);
}
