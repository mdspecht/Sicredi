package br.com.sicredi.desafio.service;

import br.com.sicredi.desafio.configuration.security.UsuarioSistema;
import br.com.sicredi.desafio.model.Votacao;
import br.com.sicredi.desafio.dto.VotacaoDTO;
import br.com.sicredi.desafio.repository.VotacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class VotacaoService {

    private final VotacaoRepository votacaoRepository;

    public void testIfVotoisValid(String voto){
        if(!voto.equalsIgnoreCase("SIM") &&
                !voto.equalsIgnoreCase("NAO"))
            throw new RuntimeException("Voto inválido");
    }

    public void testIfUserAlreadyVoted(String sessaoId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioSistema usuario = (UsuarioSistema) authentication.getPrincipal();

        this.votacaoRepository.findByEmailAndSessaoId(usuario.getEmail(), sessaoId)
                .ifPresent((v) -> {
                    throw new RuntimeException("Usuário já votou nessa sessao");
                });
    }

    public Votacao votar(VotacaoDTO votacaoDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioSistema usuario = (UsuarioSistema) authentication.getPrincipal();

        Votacao votacao = new Votacao();
        votacao.setSessaoId(votacaoDTO.getSessaoId());
        votacao.setVoto(votacaoDTO.getVoto());
        votacao.setEmail(usuario.getEmail());
        return this.votacaoRepository.insert(votacao);
    }

    public List<Votacao> findBySessaoId(String sessaoId){
        return this.votacaoRepository.findBySessaoId(sessaoId);
    }

    public Map<String, String> contarVotos(String sessaoId) {
        Map votacao = new HashMap<>();
        votacao.put("SIM", this.votacaoRepository.countBySessaoIdAndVoto(sessaoId, "SIM"));
        votacao.put("NAO", this.votacaoRepository.countBySessaoIdAndVoto(sessaoId, "NAO"));
        return votacao;
    }
}
