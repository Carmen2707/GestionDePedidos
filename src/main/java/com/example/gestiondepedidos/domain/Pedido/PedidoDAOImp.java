package com.example.gestiondepedidos.domain.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase PedidoDAOImp es la implementación de la intefaz PedidoDAO. En ella se encuentrán los métodos para acceder a los datos de los pedidos que están
 * en la base de datos.
 */
public class PedidoDAOImp implements PedidoDAO {
    /**
     * Atributo para la conexión con la base de datos.
     */
    private final Connection connection;

    /**
     * Constructor de la clase.
     *
     * @param c Es la conexión con la base de datos que se utilizará en los métodos.
     */
    public PedidoDAOImp(Connection c) {
        connection = c;
    }

    /**
     * Consulta SQL que identifica los datos de 'Pedido' según el usuario que introduzcamos.
     */
    private static final String queryLoad = "select * from pedido where usuario = ?";

    /**
     * @param userId Este id se utiliza para saber de que usuario es el pedido que deseamos obtener.
     * @return Devuelve una lista con los pedidos del usuario.
     */
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

