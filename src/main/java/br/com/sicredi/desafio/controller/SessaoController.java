package br.com.sicredi.desafio.controller;

import br.com.sicredi.desafio.dto.SessaoDTO;
import br.com.sicredi.desafio.service.PautaService;
import br.com.sicredi.desafio.service.SessaoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/sessao")
public class SessaoController extends BaseController {

    private SessaoService sessaoService;
    private PautaService pautaService;

    @ApiOperation(value = "Busca todas as sessões")
    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable){
        return new ResponseEntity<>(sessaoService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Cria uma sessão")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid SessaoDTO sessaoDTO){
        pautaService.testIfPautaExists(sessaoDTO.getPautaId());
        sessaoService.testIfHasOpenSessionWithPauta(sessaoDTO.getPautaId());

        return new ResponseEntity<>(sessaoService.create(sessaoDTO), HttpStatus.OK);
    }
}
