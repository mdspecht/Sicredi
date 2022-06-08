package br.com.sicredi.desafio.model;

import br.com.sicredi.desafio.dto.SessaoDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
public class Sessao {
    @Id
    private String id;
    @Indexed(unique=true)
    private String pautaId;
    private Integer tempoAberta;
    private String dataAbertura;
    private String dataEncerramento;
    private Status status;

    public Sessao(SessaoDTO sessaoDTO) {
        this.pautaId = sessaoDTO.getPautaId();
        this.tempoAberta = sessaoDTO.getTempoAberta();
        this.status = Status.PENDENTE;
    }

}
