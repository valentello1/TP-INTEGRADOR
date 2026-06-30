/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author valen
 */
// La <T> significa que es Genérica. 
// Cuando tus compañeros la usen, cambiarán la T por Categoria, Producto, etc.
public interface IBaseDAO<T> {

    T save(T entity) throws Exception;
    Optional<T> findById(Long id) throws Exception;
    List<T> findAll() throws Exception;
    T update(T entity) throws Exception;
    void softDelete(Long id) throws Exception;







}
