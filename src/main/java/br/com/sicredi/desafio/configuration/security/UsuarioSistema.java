package br.com.sicredi.desafio.configuration.security;

import br.com.sicredi.desafio.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UsuarioSistema extends User {

    private String email;
    private String cpf;

    public UsuarioSistema(Usuario usuario, String senha, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getEmail(), senha, authorities);
        this.email = usuario.getEmail();
        this.cpf = usuario.getCpf();
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() { return cpf; }
}