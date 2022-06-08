package br.com.sicredi.desafio.scheduler;

import br.com.sicredi.desafio.broker.QueueSender;
import br.com.sicredi.desafio.model.Sessao;
import br.com.sicredi.desafio.model.Status;
import br.com.sicredi.desafio.repository.SessaoRepository;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Configuration
public class Scheduler {

    private final SessaoRepository sessaoRepository;
    private final QueueSender queueSender;

    @Scheduled(fixedDelay = 10000)
    public void checkIfSessionEnded() {
        List<Sessao> sessao = this.sessaoRepository.findByStatus(Status.PENDENTE.toString());

        List<Sessao> sessoes = sessao.stream()
                .filter(s -> LocalDateTime.parse(s.getDataEncerramento()).isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());

        sessoes.forEach(s -> {
            s.setStatus(Status.FINALIZADA);
            queueSender.send(new Gson().toJson(s));
        });

        if(!sessoes.isEmpty())
            this.sessaoRepository.saveAll(sessoes);
    }
}
