package br.com.sicredi.desafio.service;

import br.com.sicredi.desafio.model.Pauta;
import br.com.sicredi.desafio.dto.PautaDTO;
import br.com.sicredi.desafio.repository.PautaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    @Transactional(readOnly = true)
    public Page<Pauta> findAll(Pageable page){
        return pautaRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    public Pauta findById(String id) {
        return pautaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pauta não encontrada"));
    }

    @Transactional
    public Pauta insert(PautaDTO pautaDTO){
        pautaRepository.findByTitulo(pautaDTO.getTitulo())
                .ifPresent(p -> {
                    throw new RuntimeException("Pauta já existe");
                });
        Pauta pauta = new Pauta(pautaDTO);
        //Apenas o LocalDateTime persiste com fuso horário errado
        pauta.setDataCadastro(LocalDateTime.now().toString());
        return pautaRepository.insert(pauta);
    }

    public void testIfPautaExists(String pautaId) {
        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));
    }

}
