package com.example.gestiondepedidos.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

public class UsuarioDAOImp implements UsuarioDAO {
    private final Connection connection;


    // private final static String queryLoadAll = "SELECT * FROM usuarios";
    private static final String queryLoad = "select * from usuarios where id = ?";

    private final static String queryLoadById = "SELECT * FROM usuarios WHERE nombre = ? and contraseña=?";

    public UsuarioDAOImp(Connection c) {
        connection = c;
    }

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


/*
    @Override
    public ArrayList<Usuario> loadAll() {
        var salida = new ArrayList<Usuario>();

        try(Statement st= connection.createStatement()){
            ResultSet rs = st.executeQuery(queryLoadAll);

            while(rs.next()){
                salida.add( new Usuario(rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("contraseña"),
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return salida;
    }*/
}
