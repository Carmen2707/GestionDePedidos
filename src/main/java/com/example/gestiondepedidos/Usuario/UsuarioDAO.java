package com.example.gestiondepedidos.Usuario;


public interface UsuarioDAO {
    Usuario load(Long id);

    Usuario getIdUsuario(String user, String pass);

    //  public ArrayList<Usuario> loadAll();


}
