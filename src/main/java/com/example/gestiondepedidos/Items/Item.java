package com.example.gestiondepedidos.Items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {
    private Long id;
    private String código_pedido;
    private int cantidad;
    private Long product_id;
}
