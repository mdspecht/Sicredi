package br.com.sicredi.desafio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SessaoDTO {
    @NotBlank(message = "É necessário o ID da pauta")
    private String pautaId;
    //Conforme especificado, por padrão a sessão vai durar 1 minuto se não for espeficiado
    private Integer tempoAberta = 1;
}
