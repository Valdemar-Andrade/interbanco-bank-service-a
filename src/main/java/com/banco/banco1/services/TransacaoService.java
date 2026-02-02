package com.banco.banco1.services;

import org.springframework.stereotype.Service;

import java.util.List;

public interface TransacaoService<T, K> {

    public List<T> transacoes();
}
