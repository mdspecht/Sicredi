package br.com.sicredi.desafio.controller;

import br.com.sicredi.desafio.dto.VotacaoDTO;
import br.com.sicredi.desafio.model.Sessao;
import br.com.sicredi.desafio.service.SessaoService;
import br.com.sicredi.desafio.service.UsuarioService;
import br.com.sicredi.desafio.service.VotacaoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/votacao")
public class VotacaoController extends BaseController {

    private final SessaoService sessaoService;
    private final UsuarioService usuarioService;
    private final VotacaoService votacaoService;

    @ApiOperation(value = "Conta os votos de uma sessão")
    @GetMapping
    public ResponseEntity<?> contarVotos(String sessaoId){
        Optional<Sessao> sessao = this.sessaoService.findById(sessaoId);
        if(sessao.isPresent()){
            LocalDateTime dataEncerramento = LocalDateTime.parse(sessao.get().getDataEncerramento());
            if(dataEncerramento.isBefore(LocalDateTime.now())){
                return new ResponseEntity<>(this.votacaoService.contarVotos(sessaoId), HttpStatus.OK);
            } else {
                throw new RuntimeException("Sessão não foi finalizada");
            }
        }

        sessao.orElseThrow(() -> new RuntimeException("Sessão não encontrada"));
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @ApiOperation(value = "Realiza a votação")
    @PostMapping
    public ResponseEntity<?> votar(@RequestBody @Valid VotacaoDTO votacaoDTO){
        this.sessaoService.testIfSessionIsAvailable(votacaoDTO.getSessaoId());
        this.usuarioService.testIfUsuarioCanVote();
        this.votacaoService.testIfVotoisValid(votacaoDTO.getVoto());
        this.votacaoService.testIfUserAlreadyVoted(votacaoDTO.getSessaoId());

        return new ResponseEntity<>(this.votacaoService.votar(votacaoDTO), HttpStatus.CREATED);
    }
}
