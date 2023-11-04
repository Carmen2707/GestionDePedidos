package com.example.gestiondepedidos;

import com.example.gestiondepedidos.Items.Item;
import com.example.gestiondepedidos.Items.ItemDAOImp;
import com.example.gestiondepedidos.Pedido.Pedido;
import com.example.gestiondepedidos.Productos.Producto;
import com.example.gestiondepedidos.Productos.ProductoDAOImp;
import com.example.gestiondepedidos.Usuario.Usuario;
import com.example.gestiondepedidos.Usuario.UsuarioDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.gestiondepedidos.VentanaPrincipal.userId;

public class ItemsController implements Initializable {
    @javafx.fxml.FXML
    private TableView tablaItems;
    @javafx.fxml.FXML
    private Button logout;
    @javafx.fxml.FXML
    private ImageView btnVolver;
    @javafx.fxml.FXML
    private TableColumn cIDItem;
    @javafx.fxml.FXML
    private TableColumn cCodigoItem;
    @javafx.fxml.FXML
    private TableColumn cCantidadItem;
    @javafx.fxml.FXML
    private TableColumn cProductoItem;
    @javafx.fxml.FXML
    private Button infoPedido;
    public static String código_pedido;
    List<Item> listaItems = itemsbdd(código_pedido);

    public static void setPedidoId(String código_pedido) {
        ItemsController.código_pedido = código_pedido;

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

    @javafx.fxml.FXML
    public void infoPedido(ActionEvent actionEvent) {
        UsuarioDAOImp usuarioDAO = new UsuarioDAOImp(DBConnection.getConnection());
        Usuario nombreUsuario = usuarioDAO.load(userId);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información de la ventana de los detalles del pedido");
        alert.setHeaderText("¡Hola " + nombreUsuario.getNombre()+"!");
        alert.setContentText("En esta ventana puedes ver los detalles del pedido que has seleccionado. ¡Para ver los productos de tu pedido pulsa sobre él!");
        alert.getDialogPane().setPrefSize(400, 200);

        alert.showAndWait();

    }

    @javafx.fxml.FXML
    public void clickProducto(Event event) {
        Item selectedItem = (Item) tablaItems.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            Long product_id = selectedItem.getProduct_id();
            ProductoDAOImp productoDAOImp = new ProductoDAOImp(DBConnection.getConnection());
            Producto producto = productoDAOImp.getProduct(product_id);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información del producto");
            alert.setHeaderText("Esta es la información de tu producto "+producto.getId());
            alert.setContentText("Nombre del producto: "+producto.getNombre() + "\n"
                    +"Precio del producto: "+producto.getPrecio()+ "\n"
                    +"Cantidad disponible del producto: "+producto.getCantidad_disponible());

            alert.showAndWait();
        }
    }
}
