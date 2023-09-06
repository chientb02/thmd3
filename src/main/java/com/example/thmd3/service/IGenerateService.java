package com.example.thmd3.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface IGenerateService  <E>{
    List<E> findAll () ;
    List<E> findByName(HttpServletRequest request) throws ServletException, IOException ;
    E findOne (HttpServletRequest request) throws ServletException, IOException;
    void update (HttpServletRequest request) throws ServletException, IOException ;
    void create (HttpServletRequest request) throws ServletException, IOException ;
    void delete (HttpServletRequest request) throws ServletException, IOException ;

}
