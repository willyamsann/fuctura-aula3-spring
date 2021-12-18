package com.fuctura.fuctura.model;


import javax.persistence.Column;  //A Coluna que vai ao banco
import javax.persistence.Entity;  //A Entidade de banco o nome do objeto
import javax.persistence.GeneratedValue; //Gerar valores
import javax.persistence.GenerationType; //Gerar Tipos
import javax.persistence.Id; //Id HashKey - chave primaria
import javax.persistence.Table; //Nome da tabela

@Entity
@Table(name = "ProjetosNewOne")
public class Projeto {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id; //

  
  @Column(name = "tipo")
  private String tipo; //

  @Column(name = "data")
  private String data; //

    @Column(name = "valor")
  private String valor; //
  
  @Column(name = "published")
  private boolean published;

  public Projeto() {}

  public Projeto(String tipo, String data, String valor, boolean published){
    this.tipo = tipo;
    this.data = data;
    this.valor = valor;
    this.published = published;
  }

  public long getById(){
    return id;
  }

  // TITLE
  public String getByTitle(){
    return tipo;
  }

  public void setName(String name){
    this.tipo = tipo;
  }

    public String getByValor(){
    return tipo;
  }

  public void setValor(String valor){
    this.valor = valor;
  }
  //END TITLE

  // DESCRIPTION
  public String getByDescription(){
    return data;
  }

  public void setDescription(String description){
    this.data = data;
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
    return "Projeto Fuctura id: " + id + ", name: " + tipo + ", published: " 
              + published + ", description: " + data;
  }
}
