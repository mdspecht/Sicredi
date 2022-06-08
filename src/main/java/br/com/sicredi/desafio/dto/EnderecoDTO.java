package br.com.sicredi.desafio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class EnderecoDTO {
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String cidade;
}
