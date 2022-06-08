package br.com.sicredi.desafio.model;

import br.com.sicredi.desafio.dto.EnderecoDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Endereco {
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String cidade;

    public Endereco(EnderecoDTO enderecoDTO){
        this.logradouro = enderecoDTO.getLogradouro();
        this.numero = enderecoDTO.getNumero();
        this.bairro = enderecoDTO.getBairro();
        this.cidade = enderecoDTO.getCidade();
    }
}
