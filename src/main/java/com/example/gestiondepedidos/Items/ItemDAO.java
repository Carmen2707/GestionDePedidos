package com.example.gestiondepedidos.Items;

import java.util.List;

public interface ItemDAO {
    List<Item> getItemsByPedidoId(String código_pedido);
}
