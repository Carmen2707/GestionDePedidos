package com.example.gestiondepedidos.Productos;

import java.sql.Connection;

public class ProductoDAOImp {
    private final Connection connection;

    public ProductoDAOImp(Connection c) {
        connection = c;
    }

    private static final String queryLoad = "select * from producto where id = ?";
}
