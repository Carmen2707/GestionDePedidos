package com.example.gestiondepedidos;

import com.example.gestiondepedidos.Items.Item;
import com.example.gestiondepedidos.Items.ItemDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemsController implements Initializable {
    @javafx.fxml.FXML
    private TableView tablaItems;
    @javafx.fxml.FXML
    private Button logout;
    @javafx.fxml.FXML
    private ImageView btnVolver;

    public static String código_pedido;
    List<Item> listaItems = itemsbdd(código_pedido);
    @javafx.fxml.FXML
    private TableColumn cIDItem;
    @javafx.fxml.FXML
    private TableColumn cCodigoItem;
    @javafx.fxml.FXML
    private TableColumn cCantidadItem;
    @javafx.fxml.FXML
    private TableColumn cProductoItem;


    public static void setPedidoId(String código_pedido) {
        ItemsController.código_pedido = código_pedido;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ItemDAOImp itemDAO = new ItemDAOImp(DBConnection.getConnection());


        cIDItem.setCellValueFactory(new PropertyValueFactory<>("id"));
        cCodigoItem.setCellValueFactory(new PropertyValueFactory<>("código_pedido"));
        cCantidadItem.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        cProductoItem.setCellValueFactory(new PropertyValueFactory<>("product_id"));

        ObservableList<Item> observableItems = FXCollections.observableArrayList(listaItems);
        tablaItems.setItems(observableItems);
    }

    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {
        HelloApplication.detallesPedido("Views/login.fxml");
    }

    public void volver(ActionEvent actionEvent) {
        HelloApplication.ventanaPrincipal("Views/ventana-principal.fxml");
    }

    public List<Item> itemsbdd(String código_pedido) {
        if (código_pedido != null) {
            ItemDAOImp itemDAOImp = new ItemDAOImp(DBConnection.getConnection());
            return itemDAOImp.getItemsByPedidoId(código_pedido);
        } else {
            return new ArrayList<Item>();
        }

    }
}
