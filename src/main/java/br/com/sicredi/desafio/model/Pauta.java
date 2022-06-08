package br.com.sicredi.desafio.model;

import br.com.sicredi.desafio.dto.PautaDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Document
@NoArgsConstructor
public class Pauta {
    @Id
    private String id;
    @Indexed(unique=true)
    private String titulo;
    private String descricao;
    private Endereco endereco;
    private List<PautaItem> itens;
    private String dataCadastro;

    public Pauta(PautaDTO pautaDTO){
        this.titulo = pautaDTO.getTitulo();
        this.descricao = pautaDTO.getDescricao();
        this.endereco = new Endereco(pautaDTO.getEndereco());
        this.itens = pautaDTO.getItens().stream()
                .map(PautaItem::new)
                .collect(Collectors.toList());
    }

}
