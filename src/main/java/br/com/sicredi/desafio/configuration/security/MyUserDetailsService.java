package br.com.sicredi.desafio.configuration.security;

import br.com.sicredi.desafio.model.Usuario;
import br.com.sicredi.desafio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
        Collection<GrantedAuthority> auths = new ArrayList<>();

        //senha = 'teste'
        String tempPassword = "$2a$10$NYqIcPIfrxIGrf3se1mEQeGD61AHjEzlct7YlNfhCFd21lIbZRvHa";
        return new UsuarioSistema(usuario, tempPassword, auths);
    }

}