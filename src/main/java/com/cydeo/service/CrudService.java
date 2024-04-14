package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface CrudService <T,ID> {

    T save(T t);
    T findById(ID id);
    List<T> findAll();
    void deleteById(ID id);

}
