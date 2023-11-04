package com.example.gestiondepedidos.Productos;

import com.example.gestiondepedidos.Items.Item;
import com.example.gestiondepedidos.Usuario.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImp implements ProductoDAO{
    private final Connection connection;

    public ProductoDAOImp(Connection c) {
        connection = c;
    }

    private static final String queryLoad = "select * from producto where id = ?";

    @Override
    public Producto getProduct(Long id) {
        Producto salida = null;

        try (PreparedStatement pst = connection.prepareStatement(queryLoad)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                salida= new Producto();
                salida.setId(rs.getLong("id"));
                salida.setNombre(rs.getString("nombre"));
                salida.setPrecio(rs.getString("precio"));
                salida.setCantidad_disponible(rs.getInt("cantidad_disponible"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return salida;
    }
}
