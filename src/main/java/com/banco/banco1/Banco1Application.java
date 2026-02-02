package com.banco.banco1;

import com.banco.banco1.entities.CodigoEncriptacao;
import com.banco.banco1.repositories.CodigoEncriptacaoRepository;
import com.banco.banco1.services.implementacao.CodigoEncriptacaoServiceImpl;
import com.banco.banco1.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import javax.crypto.SecretKey;

@SpringBootApplication
@EnableKafka
public class Banco1Application {


    public static void main(String[] args) {

        SpringApplication.run(Banco1Application.class, args);
    }

}
