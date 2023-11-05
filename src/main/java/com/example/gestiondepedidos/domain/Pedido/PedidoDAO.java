package com.example.gestiondepedidos.domain.Pedido;

import java.util.List;

/**
 * La interaz PedidoDAO contiene los métodos que hemos utilizado para acceder a los datos de los pedidos.
 */
public interface PedidoDAO {
    /**
     * @param userId Este id se utiliza para saber de que usuario es el pedido que deseamos obtener.
     * @return Devuelve una lista con la información del pedido de ese usuario.
     */
    List<Pedido> getPedidos(Long userId);
}
