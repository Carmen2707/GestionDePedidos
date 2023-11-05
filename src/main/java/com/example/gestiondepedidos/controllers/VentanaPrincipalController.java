package com.example.gestiondepedidos.controllers;

import com.example.gestiondepedidos.App;
import com.example.gestiondepedidos.DBConnection;
import com.example.gestiondepedidos.domain.Pedido.Pedido;
import com.example.gestiondepedidos.domain.Pedido.PedidoDAOImp;
import com.example.gestiondepedidos.domain.Usuario.Usuario;
import com.example.gestiondepedidos.domain.Usuario.UsuarioDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * La clase VentanaPrincipalController está relacionada con la ventana de pedidos.
 */
public class VentanaPrincipalController implements Initializable {
    /**
     * TableView donde se encuentran los pedidos.
     */
    @javafx.fxml.FXML
    private TableView<Pedido> tabla;
    /**
     * Button para cerrar sesión y volver a la pantalla de login.
     */
    @javafx.fxml.FXML
    private Button logout;
    /**
     * Label donde se da la bienvenida al usuario que ha iniciado sesión.
     */
    @javafx.fxml.FXML
    private Label lblUsuario;
    /**
     * TableColumn para representar a la columna id de cada pedido.
     */
    @javafx.fxml.FXML
    private TableColumn idColumnID;
    /**
     * TableColumn para representar a la columna código de cada pedido.
     */
    @javafx.fxml.FXML
    private TableColumn idColumnCodigo;
    /**
     * TableColumn para representar a la columna fecha de cada pedido.
     */
    @javafx.fxml.FXML
    private TableColumn idColumnFecha;
    /**
     * TableColumn para representar a la columna usuario de cada pedido.
     */
    @javafx.fxml.FXML
    private TableColumn idColumnUsuario;
    /**
     * TableColumn para representar a la columna total de cada pedido.
     */
    @javafx.fxml.FXML
    private TableColumn idColumnTotal;
    /**
     * Button para ver la información de la ventana.
     */
    @javafx.fxml.FXML
    private Button infoUsuario;
    /**
     * La listaPedidos se utiliza para cargar los pedidos en la tabla.
     */
    List<Pedido> listaPedidos = pedidosbdd(userId);
    /**
     * UserId almacena el ID del usuario actual.
     */
    public static Long userId;

    /**
     * El método setUserId establece el id del usuario.
     *
     * @param userId Almacena el ID del usuario actual.
     */
    public static void setUserId(Long userId) {
        VentanaPrincipalController.userId = userId;
    }

    /**
     * Declaración del itemsController.
     */
    private ItemsController itemsController;

    /**
     * Se configuran las columnas de la tabla pedidos y se llena.
     *
     * @param url            La ubicación.
     * @param resourceBundle Los recursos.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UsuarioDAOImp usuarioDAO = new UsuarioDAOImp(DBConnection.getConnection());
        //Obtenemos el nombre del usuario para la bienvenida.
        Usuario nombreUsuario = usuarioDAO.load(userId);
        if (nombreUsuario != null) {
            String nombre = nombreUsuario.getNombre();
            lblUsuario.setText("Bienvenid@ " + nombre);
        }


        idColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("código"));
        idColumnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        idColumnUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        idColumnTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        ObservableList<Pedido> observableList = FXCollections.observableArrayList(listaPedidos);
        tabla.setItems(observableList);
    }

    /**
     * El método logout cierra la sesión volviendo a la página de login.
     *
     * @param actionEvent El evento del botón.
     */
    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {
        App.ventanaPrincipal("Views/login.fxml");
    }

    /**
     * @param userId Es el id del usuario actual.
     * @return Devuelve una lista de pedidos según el id del usuario.
     */
    private List<Pedido> pedidosbdd(Long userId) {
        PedidoDAOImp pedidoDAOImp = new PedidoDAOImp(DBConnection.getConnection());
        return pedidoDAOImp.getPedidos(userId);

    }

    /**
     * El método click se acciona cuando se pulsa sobre un pedido de la tabla. Nos lleva a la ventana de los detalles del pedido seleccionado.
     *
     * @param event Evento al clickar sobre una fila.
     */
    @javafx.fxml.FXML
    public void click(Event event) {
        if (itemsController == null) {
            itemsController = new ItemsController();
        }
        Pedido selectedPedido = tabla.getSelectionModel().getSelectedItem();
        if (selectedPedido != null) {
            itemsController.setPedidoId(selectedPedido.getCódigo());
            System.out.println(tabla.getSelectionModel().getSelectedItem());
            App.detallesPedido("Views/items-view.fxml");
        }
    }

    /**
     * Este botón de información muestra una breve descripción de la ventana de los pedidos.
     *
     * @param actionEvent Evento del botón.
     */
    @javafx.fxml.FXML
    public void infoUsuario(ActionEvent actionEvent) {
        UsuarioDAOImp usuarioDAO = new UsuarioDAOImp(DBConnection.getConnection());
        Usuario nombreUsuario = usuarioDAO.load(userId);
        //Creación del alert.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información de la ventana de pedidos");
        alert.setHeaderText("¡Hola " + nombreUsuario.getNombre() + "!" + "\n" + "El correo con el que te registraste es: " + nombreUsuario.getEmail());
        alert.setContentText("En esta ventana puedes ver tus pedidos realizos." + "\n" + "¡Para ver los detalles de tu pedido pulsa sobre él!");
        alert.showAndWait();
    }
}
