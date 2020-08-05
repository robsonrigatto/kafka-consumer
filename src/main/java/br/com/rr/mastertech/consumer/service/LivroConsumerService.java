package br.com.rr.mastertech.consumer.service;

import br.com.rr.mastertech.acesso.producer.dto.AcessoClienteDTO;
import br.com.rr.mastertech.producer.dto.Livro;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

@Component
public class LivroConsumerService {

    @Value("${acesso.file.root.path}")
    private String rootPath;

    @KafkaListener(topics = "spec3-robson-rigatto-5", groupId = "grupo-do-robson-livro")
    public void consume(@Payload Livro livro) {
        System.out.println("Recebi um livro chamado " + livro.getName() + " de " + livro.getAuthor());
    }

    @KafkaListener(topics = "spec3-robson-rigatto-1", groupId = "grupo-do-robson-acesso")
    public void consume(@Payload AcessoClienteDTO acessoClienteDTO) throws IOException {
        System.out.println(String.format("Recebi um acesso do cliente %s da porta %s com acesso %s as %s",
                acessoClienteDTO.getIdCliente().toString(), acessoClienteDTO.getIdPorta().toString(),
                acessoClienteDTO.getTemAcesso(), acessoClienteDTO.getHorario().toString()));

        Path path = Paths.get(rootPath, "acesso.csv");

        List<String> lines = Arrays.asList(String.format("%s,%s,%s,%s",
                acessoClienteDTO.getIdCliente().toString(), acessoClienteDTO.getIdPorta().toString(),
                acessoClienteDTO.getTemAcesso(), acessoClienteDTO.getHorario().toString()));
        Files.write(path, lines, StandardCharsets.UTF_8, new StandardOpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.APPEND});
    }

}
