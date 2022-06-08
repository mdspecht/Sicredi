package br.com.sicredi.desafio.repository;

import br.com.sicredi.desafio.model.Votacao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface VotacaoRepository extends MongoRepository<Votacao, String> {

    Optional<Votacao> findByEmailAndSessaoId(String email, String sessao);
    List<Votacao> findBySessaoId(String sessaoId);
    int countBySessaoIdAndVoto(String sessaoId, String voto);
}
