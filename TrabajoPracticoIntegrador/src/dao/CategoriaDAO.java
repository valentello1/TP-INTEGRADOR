/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author valen
 */

import config.ConexionDB;
import entities.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements IBaseDAO<Categoria> {

    @Override
    public void crear(Categoria categoria) {
        String sql = "INSERT INTO categorias (nombre, descripcion, eliminado, createdAt) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            stmt.setBoolean(3, categoria.isEliminado());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(categoria.getCreatedAt()));

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Categoría registrada exitosamente.");
            }
            
        } catch (SQLException e) {
            System.err.println("Error al guardar la categoría: " + e.getMessage());
        }
    }

    @Override
    public Categoria obtenerPorId(Long id) {
        Categoria categoriaEncontrada = null;
        String sql = "SELECT * FROM categorias WHERE id = ? AND eliminado = false";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoriaEncontrada = new Categoria();
                    categoriaEncontrada.setId(rs.getLong("id"));
                    categoriaEncontrada.setNombre(rs.getString("nombre"));
                    categoriaEncontrada.setDescripcion(rs.getString("descripcion"));
                    categoriaEncontrada.setEliminado(rs.getBoolean("eliminado"));
                    categoriaEncontrada.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar la categoría: " + e.getMessage());
        }

        return categoriaEncontrada;
    }

    @Override
    public List<Categoria> listarTodos() {
        List<Categoria> listaCategorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias WHERE eliminado = false";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getLong("id"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categoria.setEliminado(rs.getBoolean("eliminado"));
                categoria.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());

                listaCategorias.add(categoria);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar las categorías: " + e.getMessage());
        }

        return listaCategorias;
    }

    @Override
    public void actualizar(Categoria categoria) {
        String sql = "UPDATE categorias SET nombre = ?, descripcion = ? WHERE id = ? AND eliminado = false";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            stmt.setLong(3, categoria.getId());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println(" Categoría actualizada correctamente.");
            } else {
                System.out.println("No se pudo actualizar. Es posible que la categoría no exista.");
            }

        } catch (SQLException e) {
            System.err.println(" Error al actualizar la categoría: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Long id) {
        // Baja lógica: actualizamos el campo 'eliminado' a true
        String sql = "UPDATE categorias SET eliminado = true WHERE id = ?";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Categoría eliminada con éxito (baja lógica).");
            } else {
                System.out.println("⚠No se encontró una categoría con ese ID.");
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar la categoría: " + e.getMessage());
        }
    }
}