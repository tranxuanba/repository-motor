package com.macOfBa.service;

import com.macOfBa.model.Motor;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void remove(Long id);
}
