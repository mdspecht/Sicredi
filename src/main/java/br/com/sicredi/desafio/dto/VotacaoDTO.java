package br.com.sicredi.desafio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class VotacaoDTO {
    @NotEmpty(message = "ID da sessão é obrigatório")
    private String sessaoId;
    @NotEmpty(message = "Voto é obrigatório")
    private String voto;
}
