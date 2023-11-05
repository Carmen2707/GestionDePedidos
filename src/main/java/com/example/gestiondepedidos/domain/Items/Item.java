package com.example.gestiondepedidos.domain.Items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Esta clase Item representa la información y detalles del pedido que el usuario ha seleccionado. Contiene id, el código del pedido,
 * la cantidad y el id del producto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {
    private Long id;
    private String código_pedido;
    private int cantidad;
    private Long product_id;
}
