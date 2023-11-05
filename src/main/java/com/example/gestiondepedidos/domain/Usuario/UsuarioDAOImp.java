package com.example.gestiondepedidos.domain.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * La clase UsuarioDAOImp es la implementación de la intefaz UsuarioDAO. En ella se encuentrán los métodos para acceder a los datos de los usuarios que están
 * en la base de datos.
 */
public class UsuarioDAOImp implements UsuarioDAO {
    /**
     * Atributo para la conexión con la base de datos.
     */
    private final Connection connection;
    /**
     * Consulta SQL que identifica a un usuario por su id.
     */
    private static final String queryLoad = "select * from usuarios where id = ?";
    /**
     * Consulta SQL que identifica a un usuario por su nombre y contraseña.
     */
    private final static String queryLoadById = "select * from usuarios where nombre = ? and contraseña=?";

    /**
     * Constructor de la clase.
     *
     * @param c Es la conexión con la base de datos que se utilizará en los métodos.
     */
    public UsuarioDAOImp(Connection c) {
        connection = c;
    }

    /**
     * @param id Este es el id del usuario
     * @return Devuelve un usuario según el id que se introduzca.
     */
    @Override
    public Usuario load(Long id) {
        Usuario salida = null;

        try (var pst = connection.prepareStatement(queryLoad)) {
            pst.setLong(1, id);
            var rs = pst.executeQuery();
            if (rs.next()) {
                salida = new Usuario();
                salida.setId(rs.getLong("id"));
                salida.setNombre(rs.getString("nombre"));
                salida.setContraseña(rs.getString("contraseña"));
                salida.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    /**
     * @param user Este String es el nombre del usuario.
     * @param pass Este String es la contraseña del usuario.
     * @return Devuelve un usuario según el nombre y contraseña que se introduzca.
     */
    @Override
    public Usuario getIdUsuario(String user, String pass) {
        Usuario usuario = null;
        try (var pst = connection.prepareStatement(queryLoadById)) {
            pst.setString(1, user);
            pst.setString(2, pass);
            var rs = pst.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
}
