package com.example.gestiondepedidos;

import com.example.gestiondepedidos.Pedido.Pedido;
import com.example.gestiondepedidos.Pedido.PedidoDAOImp;
import com.example.gestiondepedidos.Usuario.Usuario;
import com.example.gestiondepedidos.Usuario.UsuarioDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaPrincipal implements Initializable {
    @javafx.fxml.FXML
    private TableView<Pedido> tabla;
    @javafx.fxml.FXML
    private Button logout;
    @javafx.fxml.FXML
    private Label lblUsuario;
    @javafx.fxml.FXML
    private TableColumn idColumnID;
    @javafx.fxml.FXML
    private TableColumn idColumnCodigo;
    @javafx.fxml.FXML
    private TableColumn idColumnFecha;
    @javafx.fxml.FXML
    private TableColumn idColumnUsuario;
    @javafx.fxml.FXML
    private TableColumn idColumnTotal;


    List<Pedido> listaPedidos = pedidosbdd(userId);
    public static Long userId;

    public static void setUserId(Long userId) {

        VentanaPrincipal.userId = userId;

    }

    private ItemsController itemsController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UsuarioDAOImp usuarioDAO = new UsuarioDAOImp(DBConnection.getConnection());

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


    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {
        HelloApplication.ventanaPrincipal("Views/login.fxml");
    }

    private List<Pedido> pedidosbdd(Long userId) {
        PedidoDAOImp pedidoDAOImp = new PedidoDAOImp(DBConnection.getConnection());
        return pedidoDAOImp.getPedidos(userId);

    }

    @javafx.fxml.FXML
    public void click(Event event) {
        if (itemsController == null) {
            // Inicializa itemsController aquí
            itemsController = new ItemsController();
        }
        Pedido selectedPedido = tabla.getSelectionModel().getSelectedItem();
        if (selectedPedido != null) {
            itemsController.setPedidoId(selectedPedido.getCódigo());
            System.out.println(tabla.getSelectionModel().getSelectedItem());
            HelloApplication.detallesPedido("Views/items-view.fxml");
        }
    }
}
