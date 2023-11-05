package com.example.gestiondepedidos.controllers;

import com.example.gestiondepedidos.App;
import com.example.gestiondepedidos.DBConnection;
import com.example.gestiondepedidos.domain.Usuario.Usuario;
import com.example.gestiondepedidos.domain.Usuario.UsuarioDAO;
import com.example.gestiondepedidos.domain.Usuario.UsuarioDAOImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * La clase LoginController está relacionada con la ventana de login.
 */
public class LoginController implements Initializable {
    /**
     * TextField en el que se introduce el nombre del usuario.
     */
    @FXML
    private TextField txtUsuario;
    /**
     * Button para iniciar sesión.
     */
    @FXML
    private Button btnLogin;
    /**
     * Label que controla si el usuario es correcto o no.
     */
    @FXML
    private Label info;
    /**
     * PasswordField en el que se introduce la contraseña.
     */
    @FXML
    private PasswordField txtContraseña;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Método para validar si el usuario se encuentra en la base de datos.
     *
     * @param user String correspondiente al nombre del usuario.
     * @param pass String correspondiente a la contraseña del usuario.
     * @return Devuelve ese usuario.
     */
    public Usuario validarUsuario(String user, String pass) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImp(DBConnection.getConnection());

        return usuarioDAO.getIdUsuario(user, pass);
    }

    /**
     * El método login verifica el usuario y si es correcto lo lleva a su ventana principal.
     *
     * @param actionEvent El evento del botón de login.
     */
    @FXML
    public void login(ActionEvent actionEvent) {
        //Obtenemos los datos introducidos y se validan.
        String user = txtUsuario.getText();
        String pass = txtContraseña.getText();
        Usuario usuario = validarUsuario(user, pass);
        //Si el usuario es correcto, accede a su ventana de pedidos.
        if (usuario != null) {
            VentanaPrincipalController.setUserId(usuario.getId());
            App.ventanaPrincipal("Views/ventana-principal.fxml");
            info.setText("Usuario correcto");
        } else {
            info.setText("Usuario o contraseña incorrecto");
            txtUsuario.setText("");
            txtContraseña.setText("");
        }


    }


}