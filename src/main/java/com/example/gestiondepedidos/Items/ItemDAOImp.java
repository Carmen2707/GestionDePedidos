package com.example.gestiondepedidos.Items;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImp implements ItemDAO {
    private final Connection connection;

    public ItemDAOImp(Connection c) {
        connection = c;
    }

    private static final String queryLoad = "select * from items where código_pedido = ?";


    @Override
    public List<Item> getItemsByPedidoId(String código_pedido) {
        var salida = new ArrayList<Item>();

        try (PreparedStatement ps = connection.prepareStatement(queryLoad)) {
            ps.setString(1, código_pedido);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                salida.add(new Item(rs.getLong("id"),
                        rs.getString("código_pedido"),
                        rs.getInt("cantidad"),
                        rs.getLong("product_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return salida;
    }
}
