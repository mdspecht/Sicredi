package br.com.sicredi.desafio.dto;

import br.com.sicredi.desafio.model.Endereco;
import br.com.sicredi.desafio.model.PautaItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
public class PautaDTO {
    @NotBlank(message = "Título é um campo obrigatório")
    private String titulo;
    private String descricao;
    private EnderecoDTO endereco;
    private List<PautaItemDTO> itens;
}
