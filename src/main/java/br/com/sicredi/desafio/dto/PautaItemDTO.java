package br.com.sicredi.desafio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PautaItemDTO {
    private Integer item;
    private String assunto;
}
