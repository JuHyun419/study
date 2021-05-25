package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Component
public abstract class BaseService<Req, Res, Entity>
        implements CrudInterface<Req, Res> {

    // Item의 경우 JpaRepository<Item,Long>
    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;

}
