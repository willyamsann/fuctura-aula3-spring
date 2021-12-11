package com.fuctura.fuctura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fuctura.fuctura.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto,Long>{
  List<Projeto> findByPublished(boolean published);
  List<Projeto> findByName(String name);
}
