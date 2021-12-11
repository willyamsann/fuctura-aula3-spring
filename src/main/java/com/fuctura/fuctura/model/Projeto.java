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

  public Projeto(String name, String description, boolean published){
    this.name = name;
    this.description = description;
    this.published = published;
  }

  public long getById(){
    return id;
  }

  // TITLE
  public String getByTitle(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }
  //END TITLE

  // DESCRIPTION
  public String getByDescription(){
    return description;
  }

  public void setDescription(String description){
    this.description = description;
  }
  //END DESCRIPTION

  //PUBLISHED
  public boolean getPublished(){
    return published;
  }
  public void setPublished(boolean published){
    this.published = published;
  }

  //END PUBLISHED

  @Override
  public String toString(){
    return "Projeto Fuctura id: " + id + ", name: " + name + ", published: " 
              + published + ", description: " + description;
  }
}
