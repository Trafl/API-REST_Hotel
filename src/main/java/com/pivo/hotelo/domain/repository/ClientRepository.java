package com.pivo.hotelo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pivo.hotelo.domain.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
