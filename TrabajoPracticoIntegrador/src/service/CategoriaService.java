/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author valen
 */

import dao.CategoriaDAO;
import entities.Categoria;
import exception.EntityNotFoundException;
import java.util.List;

public class CategoriaService {
    
    // Instanciamos el DAO para comunicarnos con la base de datos
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    /**
     * Crea una nueva categoría validando que tenga al menos un nombre.
     */
    public void crearCategoria(String nombre, String descripcion) {
        // Validación de negocio: El nombre es obligatorio
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: El nombre de la categoría no puede estar vacío.");
            return; // Cortamos la ejecución para no guardar datos sucios
        }

        Categoria nuevaCategoria = new Categoria(nombre, descripcion);
        categoriaDAO.crear(nuevaCategoria);
    }

    /**
     * Lista todas las categorías activas.
     */
    public void listarCategorias() {
        List<Categoria> lista = categoriaDAO.listarTodos();
        
        if (lista.isEmpty()) {
            System.out.println("No hay categorías registradas en el sistema.");
        } else {
            System.out.println("LISTA DE CATEGORÍAS");
            for (Categoria c : lista) {
                System.out.println(c.toString());
            }
        }
    }

    /**
     * Edita una categoría existente. Lanza excepción si no la encuentra.
     */
    public void editarCategoria(Long id, String nombre, String descripcion) {
        //  Verificamos si existe en la base de datos
        Categoria categoriaExistente = categoriaDAO.obtenerPorId(id);
        
        if (categoriaExistente == null) {
            throw new EntityNotFoundException("Error: No se encontró ninguna categoría con el ID " + id);
        }
        
        // 2. Validamos los nuevos datos
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: El nuevo nombre no puede estar vacío.");
            return;
        }

        // Actualizamos el objeto
        categoriaExistente.setNombre(nombre);
        categoriaExistente.setDescripcion(descripcion);
        
        //  Mandamos los cambios al DAO
        categoriaDAO.actualizar(categoriaExistente);
    }

    /**
     * Da de baja lógica una categoría. Lanza excepción si no la encuentra.
     */
    public void eliminarCategoria(Long id) {
        Categoria categoriaExistente = categoriaDAO.obtenerPorId(id);
        
        if (categoriaExistente == null) {
            throw new EntityNotFoundException("Error: No se encontró ninguna categoría con el ID " + id + " para eliminar.");
        }
        
        categoriaDAO.eliminar(id);
    }
}