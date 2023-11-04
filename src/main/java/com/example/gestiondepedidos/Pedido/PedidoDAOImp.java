package com.example.gestiondepedidos.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImp implements PedidoDAO {
    private final Connection connection;

    public PedidoDAOImp(Connection c) {
        connection = c;
    }

    private static final String queryLoad = "select * from pedido where usuario = ?";

    @Override
    public List<Pedido> getPedidos(Long userId) {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(queryLoad);
            statement.setLong(1, userId);
            var rs = statement.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getLong("id"));
                pedido.setCódigo(rs.getString("código"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setUsuario(rs.getInt("usuario"));
                pedido.setTotal(rs.getInt("total"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }


}

