package com.example.gestiondepedidos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La clase App inicia la aplicación y carga las diferentes ventanas.
 */
public class App extends Application {
    /**
     * Es el escenario principal.
     */
    private static Stage myStage;

    /**
     * El método start carga la ventana de login.
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        this.myStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        myStage.setTitle("Login");
        myStage.setScene(scene);
        myStage.show();
    }

    /**
     * El método ventanaPrincipal carga la ventana de pedidos.
     *
     * @param fxml
     */
    public static void ventanaPrincipal(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            myStage.setTitle("Ventana principal");
            myStage.setScene(scene);
            myStage.show();
        } catch (IOException e) {
            System.out.println("Error al cargar el FXML");
            throw new RuntimeException(e);
        }
    }

    /**
     * El método detallesPedido carga la ventana de los detalles del pedido.
     *
     * @param fxml
     */
    public static void detallesPedido(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            myStage.setTitle("Detalles del pedido");
            myStage.setScene(scene);
            myStage.show();
        } catch (IOException e) {
            System.out.println("Error al cargar el FXML");
            throw new RuntimeException(e);
        }
    }

    /**
     * Inicia la aplicación.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}