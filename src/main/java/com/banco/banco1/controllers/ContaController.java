package com.banco.banco1.controllers;

import com.banco.banco1.dto.ContaDTO;
import com.banco.banco1.entities.CodigoEncriptacao;
import com.banco.banco1.entities.Conta;
import com.banco.banco1.services.implementacao.CodigoEncriptacaoServiceImpl;
import com.banco.banco1.services.implementacao.ContaServiceImpl;
import com.banco.banco1.utils.EncryptionUtil;
import com.banco.banco1.utils.ResponseBody;
import com.banco.banco1.utils.ValidacaoIBAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/conta")
public class ContaController extends BaseController{

    @Autowired
    private ContaServiceImpl service;

    @Value("${hostserver.name}")
    private String hostname;
    @Value("${hostserver.password}")
    private String password;
    @Value("${hostserver.port}")
    private String porta;

    @GetMapping
    public ResponseEntity<ResponseBody> listar(){
        return this.ok("Contas listadas com sucesso",  toDTO(this.service.findAll()));
    }

    public static List<ContaDTO> toDTO(List<Conta> contas) {

        List<ContaDTO> contaDTOs = new ArrayList<>();

        for (Conta conta : contas) {
            ContaDTO contaDto = new ContaDTO();
            contaDto.setPk_conta(conta.getPk_conta());
            contaDto.setIban(conta.getIban());
            contaDto.setSaldo_disponivel(conta.getSaldo_disponivel());
            contaDto.setSaldo_contabilistico(conta.getSaldo_contabilistico());
            contaDto.setFk_cliente(conta.getFk_cliente().getPk_cliente());

            contaDTOs.add(contaDto);
        }
        return contaDTOs;
    }

    public static ContaDTO toDTOOne(Optional<Conta> conta) {

        if (conta.isPresent()){
            Conta co = conta.get();

            ContaDTO contaDto = new ContaDTO();

            contaDto.setPk_conta(co.getPk_conta());
            contaDto.setIban(co.getIban());
            contaDto.setSaldo_disponivel(co.getSaldo_disponivel());
            contaDto.setSaldo_contabilistico(co.getSaldo_contabilistico());
            contaDto.setFk_cliente(co.getFk_cliente().getPk_cliente());

            return contaDto;
        }
        return null;
    }

    @PostMapping("/criar")
    public ResponseEntity<ResponseBody> criar(@RequestBody Conta conta){

        // Validar o IBAN
        if (!ValidacaoIBAN.validarIBAN(conta.getIban()) || !ValidacaoIBAN.verificarPrefixoIBAN(conta.getIban())) {
            return this.erro("Erro na operacao: formato do IBAN invalido", conta);
        }

        // Verificar se o fk_cliente é nulo
        if (conta.getFk_cliente() == null) {
            return this.erro("Cliente nulo", conta);
        }

        //verificar se o saldo padrao e valido
        if (conta.getSaldo_disponivel().signum() <= 0 || conta.getSaldo_contabilistico().signum() <= 0){
            return this.erro("Saldo invalido", conta);
        }

        return this.created("Conta criada com sucesso", this.service.criar(conta));
    }

    @GetMapping("/{pk_conta}")
    public ResponseEntity<ResponseBody> consultarConta(@PathVariable UUID pk_conta){
        return this.ok("Conta consultada com sucesso", toDTOOne(this.service.findById(pk_conta)));
    }

}
