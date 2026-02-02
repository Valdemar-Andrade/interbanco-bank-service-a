package com.banco.banco1.utils;

import com.banco.banco1.dto.TransacaoDTO;

import java.util.ArrayList;
import java.util.List;

public class ArrayTransacaoManipulator {

    //public static ArrayList<TransacaoDTO> arrayTransacoesRecebidas;

    public static ArrayList<TransacaoDTO> arrayTransacoes = new ArrayList<>();

    public static List<TransacaoDTO> listarTransacoes(){
        return arrayTransacoes;
    }

    public static void adicionarTransacao(TransacaoDTO t){
        arrayTransacoes.add(t);
    }
}
