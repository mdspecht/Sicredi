package br.com.sicredi.desafio.service;

import br.com.sicredi.desafio.model.Sessao;
import br.com.sicredi.desafio.dto.SessaoDTO;
import br.com.sicredi.desafio.repository.SessaoRepository;
import br.com.sicredi.desafio.repository.PautaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SessaoService {

    private final PautaRepository pautaRepository;
    private final SessaoRepository sessaoRepository;

    public Page<Sessao> findAll(Pageable pageable) {
        return sessaoRepository.findAll(pageable);
    }

    public Sessao create(SessaoDTO sessaoDTO) {
        Sessao sessao = new Sessao(sessaoDTO);
        sessao.setDataAbertura(LocalDateTime.now().toString());
        sessao.setDataEncerramento(LocalDateTime.now().plusMinutes(sessaoDTO.getTempoAberta()).toString());
        return sessaoRepository.insert(sessao);
    }

    /**
     * Testa se existe sessão aberta para a pauta
     * @param pautaId
     */
    public void testIfHasOpenSessionWithPauta(String pautaId) {
        Optional<Sessao> sessao = sessaoRepository.findByPautaId(pautaId);
        sessao.ifPresent(s -> {
            throw new RuntimeException("Sessão para essa pauta já existe");
        });
    }

    /**
     * Testar se a sessão existe
     * Testar se a sessão está no horário permitido
     *
     * @param sessaoId
     * @return
     */
    public void testIfSessionIsAvailable(String sessaoId) {
        Optional<Sessao> sessao = sessaoRepository.findById(sessaoId);
        sessao.orElseThrow(() -> new RuntimeException("Sessão não existe"));

        sessao.ifPresent(s -> {
            LocalDateTime dataEncerramento = LocalDateTime.parse(s.getDataEncerramento());
            if (dataEncerramento.compareTo(LocalDateTime.now()) <= 0){
                throw new RuntimeException("Sessão está indisponível");
            }
        });
    }

    public Optional<Sessao> findById(String sessionId) {
        return this.sessaoRepository.findById(sessionId);
    }
}
