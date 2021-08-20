package com.fomularios.application.repository;

import com.fomularios.application.entity.Cidade;
import com.fomularios.application.entity.Disciplina;
import com.fomularios.application.entity.Nota;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotaRepository extends CrudRepository<Nota, Long> {
    @Override
    List<Nota> findAll();

    Nota findById(long id);

}