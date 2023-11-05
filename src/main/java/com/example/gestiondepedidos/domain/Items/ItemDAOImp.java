package com.example.gestiondepedidos.domain.Items;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase ItemDAOImp es la implementación de la interfaz ItemDAO donde se encuentran los métodos para acceder a los datos que están en la base de datos.
 */
public class ItemDAOImp implements ItemDAO {
    /**
     * Atributo para la conexión con la base de datos.
     */
    private final Connection connection;

    /**
     * Constructor de la clase.
     *
     * @param c Es la conexión con la base de datos que se utilizará en los métodos.
     */
    public ItemDAOImp(Connection c) {
        connection = c;
    }

    /**
     * Consulta SQL que identifica los datos de 'Items' según el código de pedido que introducamos.
     */
    private static final String queryLoad = "select * from items where código_pedido = ?";

    /**
     * @param código_pedido Es el código del pedido del que se quieren cargar los datos.
     * @return Devuelve una lista de datos de 'Items' según un código de pedido.
     */
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
