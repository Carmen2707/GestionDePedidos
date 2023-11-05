module com.example.gestiondepedidos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;

    exports com.example.gestiondepedidos.controllers;
    opens com.example.gestiondepedidos to javafx.fxml;
    exports com.example.gestiondepedidos;
    exports com.example.gestiondepedidos.domain.Usuario;
    opens com.example.gestiondepedidos.domain.Usuario to javafx.fxml;
    opens com.example.gestiondepedidos.domain.Productos to javafx.base;
    opens com.example.gestiondepedidos.controllers to javafx.fxml;
    opens com.example.gestiondepedidos.domain.Items to javafx.base;
    opens com.example.gestiondepedidos.domain.Pedido to javafx.base;
}