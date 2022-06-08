package br.com.sicredi.desafio.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Votacao {
    @Id
    private String id;
    private String email;
    private String voto;
    private String sessaoId;
}
