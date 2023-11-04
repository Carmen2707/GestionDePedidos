package com.example.gestiondepedidos.Pedido;

import java.util.List;

public interface PedidoDAO {
    List<Pedido> getPedidos(Long userId);
}
