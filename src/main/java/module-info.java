module com.example.gestiondepedidos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens com.example.gestiondepedidos to javafx.fxml;
    exports com.example.gestiondepedidos;
    exports com.example.gestiondepedidos.Usuario;
    opens com.example.gestiondepedidos.Usuario to javafx.fxml;
    opens com.example.gestiondepedidos.Pedido to javafx.base;
    opens com.example.gestiondepedidos.Items to javafx.base;
    opens com.example.gestiondepedidos.Productos to javafx.base;
}