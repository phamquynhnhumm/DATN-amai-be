package com.example.amai.core.Food.service;

import java.util.List;
import java.util.Optional;

public interface IService<T, K> {
    List<T> getAll();

    Optional<T> getById(K id);

    T save(T entity);
}
