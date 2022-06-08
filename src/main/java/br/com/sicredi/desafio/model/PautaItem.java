package br.com.sicredi.desafio.model;

import br.com.sicredi.desafio.dto.PautaItemDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PautaItem {
    private Integer item;
    private String assunto;

    public PautaItem(PautaItemDTO pautaItemDTO){
        this.item = pautaItemDTO.getItem();
        this.assunto = pautaItemDTO.getAssunto();
    }
}
