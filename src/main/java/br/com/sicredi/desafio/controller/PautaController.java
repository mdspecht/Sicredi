package br.com.sicredi.desafio.controller;

import br.com.sicredi.desafio.dto.PautaDTO;
import br.com.sicredi.desafio.service.PautaService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/pauta")
public class PautaController extends BaseController {

    private final PautaService pautaService;

    @ApiOperation(value = "Busca todas as pautas")
    @GetMapping
    public ResponseEntity<?> findAll(Pageable page) {
        return new ResponseEntity<>(pautaService.findAll(page), HttpStatus.OK);
    }

    @ApiOperation(value = "Busca pauta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return new ResponseEntity<>(pautaService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Cadastra uma pauta")
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid PautaDTO pautaDTO) {
        return new ResponseEntity<>(pautaService.insert(pautaDTO), HttpStatus.CREATED);
    }

}
