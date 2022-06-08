package br.com.sicredi.desafio.service;

import br.com.sicredi.desafio.client.UsuarioClient;
import br.com.sicredi.desafio.configuration.security.UsuarioSistema;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public void testIfUsuarioCanVote(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioSistema usuario = (UsuarioSistema) authentication.getPrincipal();
        try{
            String status = this.usuarioClient.checkStatus(usuario.getCpf()).getStatus();
            if(status.equals("UNABLE_TO_VOTE")){
                throw new RuntimeException("Usuário não está apto a votar");
            }
        } catch(FeignException ex){
            throw new RuntimeException("CPF do usuário não pode ser checado");
        }

    }


}
