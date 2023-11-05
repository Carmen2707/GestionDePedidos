package com.example.gestiondepedidos.domain.Usuario;

/**
 * La interfaz UsuarioDAO contiene los métodos que hemos utilizado para acceder a los datos de los usuario.
 */
public interface UsuarioDAO {
    /**
     * @param id Este es el id del usuario
     * @return Devuelve un usuario según su id.
     */
    Usuario load(Long id);

    /**
     * @param user Este String es el nombre del usuario.
     * @param pass Este String es la contraseña del usuario.
     * @return Devuelve un usuario según su nombre y contraseña.
     */
    Usuario getIdUsuario(String user, String pass);

}
