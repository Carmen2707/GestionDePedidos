package com.example.gestiondepedidos.domain.Items;

import java.util.List;

/**
 * La interfaz ItemDAO contiene los métodos que hemos utilizado para acceder a los datos que se encuentran en Item.
 */
public interface ItemDAO {
    /**
     * @param código_pedido Es el código del pedido del que se quieren cargar los datos.
     * @return Devuelve una lista con los datos 'Items' de ese pedido.
     */
    List<Item> getItemsByPedidoId(String código_pedido);
}
