package com.fuctura.fuctura.model;


import javax.persistence.Column;  //A Coluna que vai ao banco
import javax.persistence.Entity;  //A Entidade de banco o nome do objeto
import javax.persistence.GeneratedValue; //Gerar valores
import javax.persistence.GenerationType; //Gerar Tipos
import javax.persistence.Id; //Id HashKey - chave primaria
import javax.persistence.Table; //Nome da tabela

@Entity
@Table(name = "Projetos")
public class Projeto {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id; //

  
  @Column(name = "name")
  private String name; //

  @Column(name = "description")
  private String description; //
  
  @Column(name = "published")
  private boolean published;

  public Projeto() {}
  
}
