package br.com.sicredi.desafio.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
public class Usuario {
    @Id
    private String id;
    @Indexed(unique=true)
    private String email;
    @Indexed(unique=true)
    private String cpf;
}
