package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algafood.domain.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
