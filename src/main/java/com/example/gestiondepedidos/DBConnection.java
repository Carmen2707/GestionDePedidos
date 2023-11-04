package com.example.gestiondepedidos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConnection {
    private static final Connection connection;

    private static final Logger logger;

    static {
        logger = Logger.getLogger(DBConnection.class.getName());

        String url;
        String user;
        String password;

        var cfg = new Properties();

        try {
            cfg.load(HelloApplication.class.getClassLoader().getResourceAsStream("bbdd.properties"));
            logger.info("Configuración cargada");
            url = "jdbc:mysql://" + cfg.getProperty("host") + ":" + cfg.getProperty("port") + "/" + cfg.getProperty("dbname");
            logger.info(url);
            user = cfg.getProperty("user");
            logger.info(user);
            password = cfg.getProperty("pass");
            logger.info(password);
        } catch (IOException e) {
            logger.severe("Error procesando configuración");
            throw new RuntimeException(e);
        }


        try {
            connection = DriverManager.getConnection(url, user, password);
            logger.info("Successful connection to database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection() {
        return connection;
    }
}
