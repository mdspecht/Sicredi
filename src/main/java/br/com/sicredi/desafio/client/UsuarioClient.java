package br.com.sicredi.desafio.client;

import br.com.sicredi.desafio.dto.client.UserStatusDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "users", url = "https://user-info.herokuapp.com")
public interface UsuarioClient {

    @GetMapping("/users/{cpf}")
    UserStatusDTO checkStatus(@PathVariable("cpf") String cpf);
}
