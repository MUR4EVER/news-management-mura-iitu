package iitu.edu.kz.service;

import iitu.edu.kz.service.exceptions.newsException;

import java.util.List;

public interface generalService<T> {
    T create(T t) throws newsException;
    List<T> readAll();
    T readById(Long id) throws newsException;
    T update(T t) throws newsException;
    Boolean delete(Long id) throws newsException;
}