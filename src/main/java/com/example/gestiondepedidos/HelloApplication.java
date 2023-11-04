package com.example.gestiondepedidos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage myStage;

    @Override
    public void start(Stage stage) throws IOException {
        this.myStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        myStage.setTitle("Login");
        myStage.setScene(scene);
        myStage.show();


    }

    public static void ventanaPrincipal(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            myStage.setTitle("Ventana principal");
            myStage.setScene(scene);
            myStage.show();
        } catch (IOException e) {
            System.out.println("Error al cargar el FXML");
            throw new RuntimeException(e);
        }
    }

    public static void detallesPedido(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            myStage.setTitle("Detalles del pedido");
            myStage.setScene(scene);
            myStage.show();
        } catch (IOException e) {
            System.out.println("Error al cargar el FXML");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}