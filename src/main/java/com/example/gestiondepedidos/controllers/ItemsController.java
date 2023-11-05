package com.example.gestiondepedidos.controllers;

import com.example.gestiondepedidos.App;
import com.example.gestiondepedidos.DBConnection;
import com.example.gestiondepedidos.domain.Items.Item;
import com.example.gestiondepedidos.domain.Items.ItemDAOImp;
import com.example.gestiondepedidos.domain.Productos.Producto;
import com.example.gestiondepedidos.domain.Productos.ProductoDAOImp;
import com.example.gestiondepedidos.domain.Usuario.Usuario;
import com.example.gestiondepedidos.domain.Usuario.UsuarioDAOImp;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.gestiondepedidos.controllers.VentanaPrincipalController.userId;

/**
 * La clase ItemsController está relacionada con la ventana de los detalles del pedido seleccionado.
 */
public class ItemsController implements Initializable {
    /**
     * TableView en la que se encuentran los datos del pedido.
     */
    @javafx.fxml.FXML
    private TableView tablaItems;
    /**
     * Button para cerrar sesión.
     */
    @javafx.fxml.FXML
    private Button logout;
    /**
     * Button para volver a la ventana principal.
     */
    @javafx.fxml.FXML
    private Button btnVolver;
    /**
     * TableColumn para representar a la columna id de cada item.
     */
    @javafx.fxml.FXML
    private TableColumn cIDItem;
    /**
     * TableColumn para representar a la columna código_pedido de cada item.
     */
    @javafx.fxml.FXML
    private TableColumn cCodigoItem;
    /**
     * TableColumn para representar a la columna cantidad de cada item.
     */
    @javafx.fxml.FXML
    private TableColumn cCantidadItem;
    /**
     * TableColumn para representar a la columna product_id de cada item.
     */
    @javafx.fxml.FXML
    private TableColumn cProductoItem;
    /**
     * Button para ver la información de la ventana.
     */
    @javafx.fxml.FXML
    private Button infoPedido;

    /**
     * Declaración del código de pedido.
     */
    public static String código_pedido;
    /**
     * Lista de items según el pedido que seleccionemos.
     */
    List<Item> listaItems = itemsbdd(código_pedido);


    /**
     * El método setPedidoId establece el código del pedido.
     *
     * @param código_pedido Almacena el código de pedido.
     */
    public static void setPedidoId(String código_pedido) {
        ItemsController.código_pedido = código_pedido;

    }

    /**
     * En el método initialize se configuran las columnas de la tabla y creamos el observable para llenarla.
     *
     * @param url            La ubicación.
     * @param resourceBundle Los recursos.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cIDItem.setCellValueFactory(new PropertyValueFactory<>("id"));
        cCodigoItem.setCellValueFactory(new PropertyValueFactory<>("código_pedido"));
        cCantidadItem.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        cProductoItem.setCellValueFactory(new PropertyValueFactory<>("product_id"));

        ObservableList<Item> observableItems = FXCollections.observableArrayList(listaItems);
        tablaItems.setItems(observableItems);
    }

    /**
     * El método logout cierra la sesión volviendo a la página de login.
     *
     * @param actionEvent El evento del botón.
     */
    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {
        App.detallesPedido("Views/login.fxml");
    }

    /**
     * El método volver no lleva a la ventana principal.
     *
     * @param actionEvent El evento del botón.
     */
    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {
        App.ventanaPrincipal("Views/ventana-principal.fxml");
    }

    /**
     * @param código_pedido
     * @return El método itemsbdd devuelve una lista de los items del pedido correspondiente al código introducido.
     */
    public List<Item> itemsbdd(String código_pedido) {
        if (código_pedido != null) {
            ItemDAOImp itemDAOImp = new ItemDAOImp(DBConnection.getConnection());
            return itemDAOImp.getItemsByPedidoId(código_pedido);
        } else {
            //Si no se proporciona un código de pedido válido, devuelve una lista vacía.
            return new ArrayList<Item>();
        }

    }

    /**
     * @param actionEvent Este método llamado 'infoPedido' es la creación de una ventana de alerta que informa sobre la ventana de los detalles del pedido.
     *                    Se activa al clickar sobre el botón de información o ayuda y nos proporciona una breve descripción de la ventana.
     */
    @javafx.fxml.FXML
    public void infoPedido(ActionEvent actionEvent) {
        //Obtenemos el usuario.
        UsuarioDAOImp usuarioDAO = new UsuarioDAOImp(DBConnection.getConnection());
        Usuario nombreUsuario = usuarioDAO.load(userId);

        //Creación del alert.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información de la ventana de los detalles del pedido");
        alert.setHeaderText("¡Hola " + nombreUsuario.getNombre() + "!");
        alert.setContentText("En esta ventana puedes ver los detalles del pedido que has seleccionado. ¡Para ver los productos de tu pedido pulsa sobre él!");
        alert.getDialogPane().setPrefSize(400, 200);
        alert.showAndWait();

    }

    /**
     * El método clickProducto se produce cuando se hace click sobre el producto en la tabla de items.
     * Muestra los detalles del producto seleccionado.
     *
     * @param event Se produce al clickar sobre la fila de la tabla items.
     */
    @javafx.fxml.FXML
    public void clickProducto(Event event) {
        //Obtenemos el item seleccionado.
        Item selectedItem = (Item) tablaItems.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            //Obtenemos el id del producto.
            Long product_id = selectedItem.getProduct_id();
            //Obtenemos el producto.
            ProductoDAOImp productoDAOImp = new ProductoDAOImp(DBConnection.getConnection());
            Producto producto = productoDAOImp.getProduct(product_id);

            //Creación del alert.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información del producto");
            alert.setHeaderText("Esta es la información de tu producto " + producto.getId());
            alert.setContentText("Nombre del producto: " + producto.getNombre() + "\n"
                    + "Precio del producto: " + producto.getPrecio() + "\n"
                    + "Cantidad disponible del producto: " + producto.getCantidad_disponible());

            alert.showAndWait();
        }
    }


}
