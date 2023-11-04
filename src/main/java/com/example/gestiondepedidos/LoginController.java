package com.example.gestiondepedidos;

import com.example.gestiondepedidos.Usuario.Usuario;
import com.example.gestiondepedidos.Usuario.UsuarioDAO;
import com.example.gestiondepedidos.Usuario.UsuarioDAOImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    private TextField txtUsuario;
    @FXML
    private Button btnLogin;
    @FXML
    private Label info;
    @FXML
    private PasswordField txtContraseña;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Usuario validarUsuario(String user, String pass) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImp(DBConnection.getConnection());

        return usuarioDAO.getIdUsuario(user, pass);
    }

    @FXML
    public void login(ActionEvent actionEvent) {
        String user = txtUsuario.getText();
        String pass = txtContraseña.getText();
        Usuario usuario = validarUsuario(user, pass);
        if (usuario != null) {
            VentanaPrincipal.setUserId(usuario.getId());

            HelloApplication.ventanaPrincipal("Views/ventana-principal.fxml");
        } else {
            info.setText("Usuario no existe");
            txtUsuario.setText("");
            txtContraseña.setText("");
        }


    }


}