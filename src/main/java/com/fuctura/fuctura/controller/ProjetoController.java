package com.fuctura.fuctura.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fuctura.fuctura.repository.ProjetoRepository;
import com.fuctura.fuctura.model.Projeto;

@CrossOrigin(origins = "http://localhost:4200" )
@RestController
@RequestMapping("/api")
public class ProjetoController {
  
  @Autowired
  ProjetoRepository projetoRepository;

  @GetMapping("/projetos")
  public ResponseEntity<List<Projeto>> getAllProjectos(@RequestParam(required = false) String name) {
    try{
      List<Projeto> projetos = new ArrayList<Projeto>();
      if(name == null){
        projetoRepository.findAll().forEach(projetos::add);
      }else{
        projetoRepository.findByTipo(name).forEach(projetos::add);
      }

      if(projetos.isEmpty()){
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(projetos,HttpStatus.OK);
      
    }catch(Exception e){
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/projetos/{id}")
  public ResponseEntity<Projeto> getById(@PathVariable("id") long id){
    try{
      Optional<Projeto> _projeto = projetoRepository.findById(id);

      if(_projeto.isPresent()){
        return new ResponseEntity<>(_projeto.get(), HttpStatus.OK);
      }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

    }catch(Exception e){
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      
    }   
  }
  
  @PostMapping("/projetos")
  public ResponseEntity<Projeto> createProjeto(@RequestBody Projeto obj){
    try{
      Projeto _obj = projetoRepository.save(new Projeto(obj.getByTitle(),obj.getByDescription(), obj.getByValor(), false));
      return new ResponseEntity<>(_obj,HttpStatus.OK);
    }catch(Exception e){
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/projetos/{id}")
  public ResponseEntity<Projeto> updateProjeto(@PathVariable("id") long id, @RequestBody Projeto obj)
  {
    try{
    		Optional<Projeto> _obj = projetoRepository.findById(id);
        if(_obj.isPresent()){
          Projeto _projeto = _obj.get();  
          _projeto.setName(obj.getByTitle());
          _projeto.setDescription(obj.getByDescription());
          _projeto.setPublished(obj.getPublished());
          return new ResponseEntity<>(projetoRepository.save(_projeto), HttpStatus.OK);
        }else{
			    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }catch(Exception e){
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      
    }
  }

  @DeleteMapping("/projetos/{id}")
  public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id){
    try{
        Optional<Projeto> _obj = projetoRepository.findById(id);
        if(_obj.isPresent()){
          projetoRepository.deleteById(id);
          return new ResponseEntity<>(HttpStatus.OK);

        }else{
			    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
    catch(Exception e){
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      
    }
  }

  @DeleteMapping("/projetos")
  public ResponseEntity<HttpStatus> deleteAll(){
    try{
      projetoRepository.deleteAll();
      return new ResponseEntity<>(null, HttpStatus.OK);
    }catch(Exception e){
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/projetos/published")
  public ResponseEntity<List<Projeto>> getPublisheds(){
      try{
        List<Projeto> _projetos = projetoRepository.findByPublished(true);
        if(_projetos.isEmpty())
        {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
          return new ResponseEntity<>(_projetos,HttpStatus.OK);
        }
      }catch(Exception e){
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  
      }
  }
}
