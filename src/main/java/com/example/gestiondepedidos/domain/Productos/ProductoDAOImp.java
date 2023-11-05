package com.example.gestiondepedidos.domain.Productos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La clase ProductoDAOImp es la implementación de la interfaz ProductoDAO. En ella se encuentrán los métodos para acceder a los datos de los productos que están
 * en la base de datos.
 */
public class ProductoDAOImp implements ProductoDAO {
    /**
     * Atributo para la conexión con la base de datos.
     */
    private final Connection connection;

    /**
     * Contructor de la clase.
     *
     * @param c Es la conexión con la base de datos que se utilizará en los métodos.
     */
    public ProductoDAOImp(Connection c) {
        connection = c;
    }

    /**
     * Consulta SQL que selecciona un producto según su id.
     */
    private static final String queryLoad = "select * from producto where id = ?";

    /**
     * @param id Este id lo utilizamos para saber que producto solicitamos.
     * @return Devuelve un objeto producto según su id de la base de datos.
     */
    @Override
    public Producto getProduct(Long id) {
        Producto salida = null;

        try (PreparedStatement pst = connection.prepareStatement(queryLoad)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                salida = new Producto();
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
