package com.banco.banco1.repositories;

import com.banco.banco1.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {
        Conta findByIban(String iban);
}
