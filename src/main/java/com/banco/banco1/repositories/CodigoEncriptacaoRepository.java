package com.banco.banco1.repositories;

import com.banco.banco1.entities.CodigoEncriptacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CodigoEncriptacaoRepository extends JpaRepository<CodigoEncriptacao, UUID> {

}
